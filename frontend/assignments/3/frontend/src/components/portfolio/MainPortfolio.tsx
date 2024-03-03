import React, { useEffect, useState, useRef } from "react"; 
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../redux/store/store";
import { fetchPortfolioData } from "../redux/thunk/portfolioThunk";
import { setFilter } from "../redux/slice/portfolioSlice";
import LinearProgress from "@mui/material/LinearProgress";
import TableContainer from "@mui/material/TableContainer";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import { VariableSizeList as List } from "react-window";
import { Button } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import debounce from "lodash/debounce";
import ReusableSnackbar from "../helpers/ReusableSnackbar";
import { PortfolioItem } from "../types/PortfolioItem";
import { MemoizedListItem } from "./PortfolioListItem";
import {DivMainContainer, StyledContainer,StyledHr} from "./MainPortfolioStyles"

export function MainPortfolio() {

  const dispatch: AppDispatch = useDispatch();
  const username = useSelector((state: RootState) => state.user.user);
  const portfolioItems = useSelector(
    (state: RootState) => state.portfolio.portfolioItems
  );
  const isLoading = useSelector((state: RootState) => state.portfolio.loading);
  const isError = useSelector((state: RootState) => state.portfolio.error);
  const searchInputRef = useRef<HTMLInputElement>(null);
  const startDateRef = useRef<HTMLInputElement>(null);
  const endDateRef = useRef<HTMLInputElement>(null);
  const passedCheckboxRef = useRef<HTMLInputElement>(null);
  const failedCheckboxRef = useRef<HTMLInputElement>(null);
  const filterButtonRef = useRef(null);
  const [isMobileView, setIsMobileView] = useState(false);
  const showFilters = useSelector((state: RootState) => state.portfolio.showFilters);
  const selectedStockNamesRef = useRef<string[]>([]);

  const [filterValues, setFilterValues] = useState({
    searchQuery: "",
    startDate: "",
    endDate: "",
    selectedStockNames: [],
    filterPassed: false,
    filterFailed: false,
  });
  interface TransactionsByDate {
    [date: string]: PortfolioItem[]; 
  }
  
  useEffect(() => {
    if (window.innerWidth <= 768) {
      setIsMobileView(true);
    } else {
      setIsMobileView(false);
    }
  }, []);
  useEffect(() => {
    const handleResize = () => {
      if (window.innerWidth <= 768) {
        setIsMobileView(true);
      } else {
        setIsMobileView(false);
      }
    };

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);
  useEffect(() => {
    dispatch(fetchPortfolioData(username));
  }, [dispatch, username]);
  const handleSnackbarClose = (event: React.SyntheticEvent, reason: string) => {
    if (reason === "clickaway") {
      return;
    }
    console.log("Snackbar closed:", event);

  };
  const transactionsByDate: TransactionsByDate = portfolioItems.reduce((acc: TransactionsByDate, item) => {
    const date = new Date(item.date).toLocaleDateString("en-US", {
      day: "2-digit",
      month: "short",
      year: "numeric",
    });
    if (!acc[date]) {
      acc[date] = [];
    }
    acc[date].push(item);
    return acc;
  }, {});
  const handleFilterChange = (name:string, value:any) => {
    setFilterValues((prevValues) => ({
      ...prevValues,
      [name]: value,
    }));
  };
  useEffect(() => {
    const delayedFilter = debounce(() => {
      if (searchInputRef.current) {
        console.log(
          "searchInputRef.current.value",
          searchInputRef.current.value
        );
        handleFilterChange("searchQuery", searchInputRef.current.value);
      }
  
      if (startDateRef.current) {
        handleFilterChange("startDate", startDateRef.current.value);
      }
      if (endDateRef.current) {
        handleFilterChange("endDate", endDateRef.current.value);
      }

      if (passedCheckboxRef.current) {
        handleFilterChange("filterPassed", passedCheckboxRef.current.checked);
      }
      if (failedCheckboxRef.current) {
        handleFilterChange("filterFailed", failedCheckboxRef.current.checked);
      }
      if(selectedStockNamesRef.current.length > 0){
        handleFilterChange("selectedStockNames", selectedStockNamesRef.current);
      }
    }, 500);

    if (searchInputRef.current) {
      searchInputRef.current.addEventListener("input", delayedFilter);
    }

    if (startDateRef.current) {
      startDateRef.current.addEventListener("input", delayedFilter);
    }

    if (endDateRef.current) {
      endDateRef.current.addEventListener("input", delayedFilter);
    }

    if (passedCheckboxRef.current) {
      passedCheckboxRef.current.addEventListener("input", delayedFilter);
    }

    if (failedCheckboxRef.current) {
      failedCheckboxRef.current.addEventListener("input", delayedFilter);
    }

    handleFilterChange("selectedStockNames", selectedStockNamesRef.current);

    return () => {
      if (searchInputRef.current) {
        searchInputRef.current.removeEventListener("input", delayedFilter);
      }
      if (startDateRef.current) {
        startDateRef.current.removeEventListener("input", delayedFilter);
      }
      if(endDateRef.current)
      {
        endDateRef.current.removeEventListener("input", delayedFilter);
      }

      if (passedCheckboxRef.current) {
        passedCheckboxRef.current.removeEventListener("input", delayedFilter);
      }
      if (failedCheckboxRef.current) {
        failedCheckboxRef.current.removeEventListener("input", delayedFilter);
      } 

    };
  }, [handleFilterChange]); 


  const applyFilters = (transactions:PortfolioItem[]) => {
    const lowerCaseSearchQuery = filterValues.searchQuery.toLowerCase();
    const startDate = filterValues.startDate
      ? new Date(filterValues.startDate)
      : null;
    const endDate = filterValues.endDate
      ? new Date(filterValues.endDate)
      : null;

    const filteredTransactions = transactions.filter((transaction:PortfolioItem) => {
      const stockMatch =
        transaction.stockname.toLowerCase().includes(lowerCaseSearchQuery) ||
        transaction.symbol.toLowerCase().includes(lowerCaseSearchQuery);

      const transactionDate = new Date(transaction.date);
      const dateMatch =
        (!startDate || transactionDate >= startDate) &&
        (!endDate || transactionDate <= endDate);

      const stockNameMatch =
        filterValues.selectedStockNames.length === 0 ||
        filterValues.selectedStockNames.includes(transaction.stockname);

      const successMatch =
        (!filterValues.filterPassed || transaction.type === "Passed") &&
        (!filterValues.filterFailed || transaction.type !== "Passed");

      return stockMatch && dateMatch && stockNameMatch && successMatch;
    });

    return filteredTransactions;
  };

  const uniqueStockNames = [
    ...new Set(portfolioItems.map((item) => item.stockname)),
  ];
  const clearAllFilters = () => {
    setFilterValues({
      searchQuery: "",
      startDate: "",
      endDate: "",
      selectedStockNames: [],
      filterPassed: false,
      filterFailed: false,
    });
    if(searchInputRef.current)
    searchInputRef.current.value = "";
    if(startDateRef.current)
    startDateRef.current.value = "";
    if(endDateRef.current)
    endDateRef.current.value = "";
    
    if (passedCheckboxRef.current) {
      passedCheckboxRef.current.checked = false;
    }
    if (failedCheckboxRef.current) {
      failedCheckboxRef.current.checked = false;
    }
  };
  return (
    <div>
      {isLoading ? (
        <LinearProgress />
      ) : (
        <Box
          display="flex"
          style={{
            justifyContent: "space-evenly",
            position: "relative",
            flexDirection: isMobileView ? "column" : "row",
          }}
        >
         {
          isMobileView && showFilters || !isMobileView ? (
            <StyledContainer isMobileView={isMobileView} fixed>
         
            <Box
              display="flex"
              justifyContent="space-between"
              alignItems="center"
            >
              <Typography variant="h6" style={{ marginBottom: "1rem" }}>
                Filters
              </Typography>
              <Button
                variant="contained"
                style={{
                  color: "#1871c2",
                  backgroundColor: "transparent",
                  boxShadow: "none",
                }}
                onClick={clearAllFilters}
              >
                Clear All
              </Button>
            </Box>
            <StyledHr />

            <DivMainContainer
              style={{
                display: "flex",
                alignItems: "center",
                width: "80%",
                border: "1px solid #d0d0d0",
                borderRadius: "1rem",
                padding: "0.5rem",
                backgroundColor: "#f8f8f8",
              }}
            >
              <SearchIcon style={{ marginRight: "0.5rem" }} />{" "}
              <input
                type="text"
                id="searchQuery"
                placeholder="Search by Stock Name"
                ref={searchInputRef}
                style={{
                  width: "100%",
                  border: "none",
                  outline: "none",
                  backgroundColor: "transparent",
                  fontFamily: "Poppins, sans-serif",
                }}
              />
            </DivMainContainer>
            <StyledHr />


            <div
              style={{
                display: "flex",
                justifyContent: "space-between",
                marginBottom: "1rem",
              }}
            >
              <input
                type="date"
                placeholder="Start Date"
                ref={startDateRef}
                style={{
                  width: "48%",
                  border: "1px solid #d0d0d0",
                  borderRadius: "1rem",
                  padding: "0.5rem",
                  backgroundColor: "#f8f8f8",
                }}
              />
              <input
                type="date"
                placeholder="End Date"
                ref={endDateRef}
                style={{
                  width: "48%",
                  border: "1px solid #d0d0d0",
                  borderRadius: "1rem",
                  padding: "0.5rem",
                  backgroundColor: "#f8f8f8",
                }}
              />
            </div>
            <StyledHr />


            <div>
              <FormControlLabel
                control={
                  <Checkbox
                    inputRef={passedCheckboxRef}
                    onChange={(e) =>
                      handleFilterChange("filterPassed", e.target.checked)
                    }
                  />
                }
                label="Passed"
              />
            </div>

            <div>
              <FormControlLabel
                control={
                  <Checkbox
                    inputRef={failedCheckboxRef}
                    onChange={(e) =>
                      handleFilterChange("filterFailed", e.target.checked)
                    }
                  />
                }
                label="Failed"
              />
            </div>
            <StyledHr />



              <div style={{ marginBottom: "1rem" }}>

                {uniqueStockNames.length > 0 && (
                  <div style={{ marginBottom: "1rem" }}>
                    <Typography variant="subtitle1">Stock Names</Typography>
                    {uniqueStockNames.map((item:string) => (
                      <div key={item}>
                        <FormControlLabel
                          control={
                            <Checkbox
                            checked={filterValues.selectedStockNames.includes(item)}
                            onChange={(e) => {
                              const isChecked = e.target.checked;
                              if (isChecked) {
                                selectedStockNamesRef.current = [
                                  ...selectedStockNamesRef.current,
                                  item,
                                ];
                              } else {
                                selectedStockNamesRef.current = selectedStockNamesRef.current.filter(
                                  (name) => name !== item
                                );
                              }
                            }}
                          />
                          }
                          label={item}
                        />
                      </div>
                    ))}
                  </div>
                )}
              </div>
              
         
            <Button color="primary" ref={filterButtonRef} onClick={() => dispatch(setFilter(false))}>Apply</Button>
          </StyledContainer>
          ):
          (isMobileView && <Button color="primary" onClick={() => dispatch(setFilter(true))}>Filter</Button>)}

          
          <Box>
            {Object.entries(transactionsByDate).map(
              ([date, transactions]) => (
                <div key={date} style={{ marginBottom: "20px" }}>
                  {applyFilters(transactions).length > 0 && ( 
                    <>
                      <Typography
                        variant="body1"
                        style={{ margin: "1rem", color: "grey" }}
                      >
                        {date}
                      </Typography>
                      <div
                        style={{
                          borderBottom: "1px dotted #d0d0d0",
                          marginBottom: "1rem",
                        }}
                      />

                      <TableContainer
                        component={Paper}
                        style={{ height: "fit-content" }}
                      >
                        <List
                          itemCount={applyFilters(transactions).length}
                          itemSize={() => 50} 
                          width={900}
                          height={200} 
                        >
                          {({ index, style }) => (
                            <MemoizedListItem
                              index={index}
                              style={style}
                              transactions={applyFilters(transactions)}
                            />
                          )}
                        </List>
                      </TableContainer>
                    </>
                  )}
                </div>
              )
            )}
          </Box>
        </Box>
      )}
      <ReusableSnackbar open={isError} onClose={handleSnackbarClose} message={"Error Loading Items"} />
    </div>
  );
}
