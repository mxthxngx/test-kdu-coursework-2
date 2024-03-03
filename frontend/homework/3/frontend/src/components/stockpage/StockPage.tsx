import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { FlexDivContainer } from "../utils/ui/layout/FlexDivContainer";
import BoxOutline from "../utils/ui/layout/BoxOutline";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../redux/store/store";
import MenuItem from "@mui/material/MenuItem";
import Menu from "@mui/material/Menu";
import Typography from "@mui/material/Typography";
import { LinearProgress, ListItemText } from "@mui/material";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogActions from "@mui/material/DialogActions";
import Button from "@mui/material/Button";
import { DashboardItem } from "../types/DashboardItem";
import ArrowUpwardIcon from "@mui/icons-material/ArrowUpward";
import { ArrowDownward } from "@mui/icons-material";
import { History } from "./History";
import { HistoryItem, TransactionType } from "../types/HistoryItem";
import { clearhistory, sethistory } from "../redux/slice/priceSlice";
import { GlobalHistory } from "./GlobalHistory";
import { SocketProps } from "../types/SocketProps";
import ReusableSnackbar from "../helpers/ReusableSnackbar";
import { PriceInput } from "./StockPageStyles";


export function StockPage({ socket }: Readonly<SocketProps>) {
  const username: string = useSelector((state: RootState) => state.user.user);
  const dispatch: AppDispatch = useDispatch();
  const [priceHistory, setPriceHistory] = useState<number[]>([]);
  const { symbol: currentSymbol } = useParams<{ symbol: string }>();
  const dashboardItems = useSelector(
    (state: RootState) => state.dashboard.dashboardItems
  );
  const filteredItems = dashboardItems.filter(
    (item: DashboardItem) => item.stock_symbol === currentSymbol
  );
  const isLoading = useSelector((state: RootState) => state.dashboard.loading);
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
  const [showSnackbar, setShowSnackbar] = useState(false);
  const navigate = useNavigate();
  const [price, setPrice] = useState<number | null>(null);
  const currentStockName =
    filteredItems.length > 0 ? filteredItems[0].stock_name : "";
  const [snackbarMessage, setSnackbarMessage] = useState("");
  const [quantity, setQuantity] = useState<string>("");
  const [isConfirmationOpen, setIsConfirmationOpen] = useState(false);
  const [confirmationType, setConfirmationType] = useState("");
  const [confirmationQuantity, setConfirmationQuantity] = useState(0);

  useEffect(() => {
    setPriceHistory([]);
    dispatch(clearhistory());
    if (socket) {
      socket.emit("join-room", currentSymbol);
    }
  }, [currentSymbol]);

  useEffect(() => {
    const intervalId = setInterval(() => {
      fetchPrice();
    }, 5000);
    if (socket) {
      socket.emit("join-room", currentSymbol);
    }
    return () => clearInterval(intervalId);
  }, [currentSymbol, dashboardItems, dispatch]);

  const fetchPrice = async () => {
    try {
      const response = await fetch(
        `http://localhost:3000/dashboard/price/stock/${currentSymbol}`
      );
      if (!response.ok) {
        throw new Error("Failed to fetch price");
      }
      const data = await response.json();
      setPrice(data.price);
      updatePriceHistory(data.price);
    } catch (error) {
      console.error("Error fetching price:", error);

      handleSnackbar("Failed to fetch price, Invalid stock name");
    }
  };

  const updatePriceHistory = (newPrice: number) => {
    setPriceHistory((prevHistory) => [...prevHistory, newPrice]);
  };

  const handleMenuItemClick = (symbol: string) => {
    navigate(`/stock/${symbol}`);
    setAnchorEl(null);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleCloseSnackbar = () => {
    setShowSnackbar(false);
  };
  const handleConfirmationOpen = (type: string, quantity: number) => {
    setConfirmationType(type);
    setConfirmationQuantity(quantity);
    setIsConfirmationOpen(true);
  };

  const handleConfirmationClose = () => {
    setIsConfirmationOpen(false);
  };

  const handleConfirmationProceed = () => {
    setIsConfirmationOpen(false);
    if (confirmationType === "buy") {
      historyUpdate("buy", confirmationQuantity);
    } else if (confirmationType === "sell") {
      historyUpdate("sell", confirmationQuantity);
    }
  };

  const handleSnackbar = (message: string) => {
    setShowSnackbar(true);
    setSnackbarMessage(message);
  };
  const historyUpdate = (typeOfTransaction: string, quantity: number) => {
    if (price !== null && quantity !== null) {
      console.log("price:", price, "quantity:", quantity);
      const transactionObject: HistoryItem = {
        type:
          typeOfTransaction === "buy"
            ? TransactionType.BUY
            : TransactionType.SELL,
        price: price,
        quantity: quantity,
        date: new Date().toISOString(),
      };
      const requestData = {
        amount: price * quantity,
        stockname: currentStockName,
        symbol: currentSymbol,
        price: price,
        date: new Date().toISOString(),
        username: username,
      };
      fetch("http://localhost:3000/user/maketransaction", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(requestData),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Insufficient balance");
          }
          return response.json();
        })
        .then((data) => {
          console.log("Transaction successful:", data);
          socket?.emit("transaction", currentSymbol, transactionObject);
          console.log(
            "emitting transaction: ",
            transactionObject,
            currentSymbol
          );
          setQuantity("");
          dispatch(sethistory(transactionObject));
        })
        .catch((error) => {
          console.error("Transaction failed:", error);
          handleSnackbar("Transaction failed. Please try again. " + error);
        });
    }
  };

  return (
    <>
      {priceHistory.length == 0 ? (
        <LinearProgress />
      ) : (
        <FlexDivContainer width="100%">
          {isLoading ? <LinearProgress /> : null}
          <FlexDivContainer
            width="72%"
            style={{
              flexDirection: "column",
              marginLeft: "1rem",
            
            }}
          >
            <FlexDivContainer justifyCon="space-evenly">
              {filteredItems.length > 0 ? (
                <BoxOutline style={{ width: "20rem", height: "3rem" }}>
                  <Typography
                    onClick={(event) => setAnchorEl(event.currentTarget)}
                    style={{ cursor: "pointer" }}
                  >
                    <FlexDivContainer justifyCon="space-between" width="15rem">
                      <div
                        style={{
                          backgroundColor: "#FFECB3",
                          color: "orange",
                          border: "1px solid orange",
                        }}
                      >
                        {filteredItems[0].stock_symbol}
                      </div>
                      &nbsp;
                      <div>{filteredItems[0].stock_name}</div> ▼
                    </FlexDivContainer>
                  </Typography>
                  <Menu style={{height:'500px'}}
                    anchorEl={anchorEl}
                    open={Boolean(anchorEl)}
                    onClose={handleClose}
                  >
                    {dashboardItems.map((item) => (
                      <MenuItem
                        key={item.stock_symbol}
                        onClick={() => handleMenuItemClick(item.stock_symbol)}
                      >
                        <ListItemText
                          primary={
                            <div>
                            <div
                              style={{
                                display: "flex",
                                justifyContent: "space-between",
                              }}
                            >
                              <div
                                style={{
                                  backgroundColor: "#FFECB3",
                                  color: "orange",
                                  border: "1px solid orange",
                                  padding: "5px",
                                }}
                              >
                                {item.stock_symbol}
                              </div>
                              <div>{item.stock_name}</div>
                            </div>
                            <hr/>
                            </div>
                          }
                        />{" "}
                      </MenuItem>
                    ))}
                  </Menu>
                </BoxOutline>
              ) : null}
              <BoxOutline style={{ width: "30rem" }}>
                <FlexDivContainer width="30rem">
                  <div
                    style={{
                      display: "flex",
                      justifyContent: "space-around",
                      width: "20rem",
                    }}
                  >
                    {" "}
                    Price:{" "}
                    <div
                      style={{
                        color:
                          price && price < priceHistory[priceHistory.length - 2]
                            ? "red"
                            : "green",
                      }}
                    >
                      {price ?? "--"}
                    </div>
                    <div
                      style={{
                        color:
                          price && price < priceHistory[priceHistory.length - 2]
                            ? "red"
                            : "green",
                      }}
                    >
                      {price &&
                      price < priceHistory[priceHistory.length - 2] ? (
                        <ArrowDownward />
                      ) : (
                        <ArrowUpwardIcon />
                      )}
                    </div>
                    <div></div>
                  </div>
                  {price &&
                  !isNaN(
                    ((price - priceHistory[priceHistory.length - 2]) /
                      priceHistory[priceHistory.length - 2]) *
                      100
                  )
                    ? (
                        ((price - priceHistory[priceHistory.length - 2]) /
                          priceHistory[priceHistory.length - 2]) *
                        100
                      ).toFixed(2)
                    : "--"}
                  %{" "}
                </FlexDivContainer>
              </BoxOutline>
              <BoxOutline style={{ width: "20rem" }}>
                <PriceInput
                  type="number"
                  placeholder="Enter QTY"
                 
                  value={quantity || ""}
                  onChange={(e) => setQuantity(e.target.value)}
                />{" "}
              </BoxOutline>
              <BoxOutline
                bgColor="#B2F266"
                borderColor="#2F9E44"
                style={{ color: "#2F9E44", cursor: "pointer" }}
                onClick={() => handleConfirmationOpen("buy", Number(quantity))}
              >
                BUY
              </BoxOutline>
              <BoxOutline
                bgColor="#FFC9C9"
                borderColor="#E03131"
                style={{ color: "#E03131", cursor: "pointer" }}
                onClick={() => handleConfirmationOpen("sell", Number(quantity))}
              >
                SELL
              </BoxOutline>
            </FlexDivContainer>
            <div
              style={{
                position: "relative",
                height: "73%",
                overflowX: "auto",

                
                paddingTop: "10px",
                marginBottom: "20px",
                        
              }}
            >
            <div style={{
               backgroundColor:'#fff',
               backgroundSize:"10px 10px, 111px 111.2px",
               width:"200%",
               backgroundImage: 'linear-gradient(to bottom, transparent 5px, #fff 5px), linear-gradient(to right, #000 1px, transparent 1px),  linear-gradient(to right, transparent 5px, #fff 5px), linear-gradient(to bottom, #000 1px, transparent 1px) ',
               borderTop: "1px solid black",
               borderRight: "1px solid black",
               borderLeft: "1px solid black",
            }}>
              <div
                style={{
                  display: "flex",
                  justifyContent: "flex-start",
                  position: "relative",
                  transform: "rotateX(180deg)",
                  width: `${20 * priceHistory.length}px`,
                }}
              >
                {priceHistory.length == 0 ? <LinearProgress /> : null}
                {priceHistory.map((price, index) => (
                  <div
                    key={price}
                    style={{
                      width: "20px",
                      backgroundColor:
                        price > (priceHistory[index - 1] || 0)
                          ? "#B2F266"
                          : "#FFC9C9",
                      marginRight: "2px",
                      height: `${(price / Math.max(...priceHistory)) * 450}px`,
                      border: "1px solid",
                      borderColor:
                        price > (priceHistory[index - 1] || 0)
                          ? "#2F9E44"
                          : "#E03131",
                    }}
                  ></div>
                ))}
              </div>
              </div>
              <div
                style={{
                  display: "flex",
                  position: "absolute",
                  left: "0",

                  width: "200%",
                  borderTop: "1px solid",
                  height: "auto",
                }}
              >
                {Array.from(Array(51).keys()).map((num) => (
    <div
      key={num}
      style={{ position: "absolute", left: `${num * 100}px` }}
    >
      {num * 100}
    </div>
  ))}
              </div>
            </div>
          </FlexDivContainer>
          <FlexDivContainer
            width="30%"
            justifyCon="space-between"
            style={{ flexDirection: "column" }}
          >
            <History />
            <GlobalHistory socket={socket} />
          </FlexDivContainer>
          <Dialog open={isConfirmationOpen} onClose={handleConfirmationClose}>
            <DialogTitle>Confirmation</DialogTitle>
            <DialogContent>
              <DialogContentText>
                Are you sure you want to {confirmationType}{" "}
                {confirmationQuantity} shares?
              </DialogContentText>
            </DialogContent>
            <DialogActions>
              <Button onClick={handleConfirmationClose} color="primary">
                Cancel
              </Button>
              <Button
                onClick={handleConfirmationProceed}
                color="primary"
                autoFocus
              >
                Confirm
              </Button>
            </DialogActions>
          </Dialog>
          <ReusableSnackbar
            open={showSnackbar}
            onClose={handleCloseSnackbar}
            message={snackbarMessage}
          />
        </FlexDivContainer>
      )}
    </>
  );
}
