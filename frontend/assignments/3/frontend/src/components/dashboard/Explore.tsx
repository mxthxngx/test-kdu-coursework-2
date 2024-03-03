import * as React from 'react';
import Table from '@mui/material/Table';
import TableHead from '@mui/material/TableHead';
import TableBody from '@mui/material/TableBody';
import TableRow from '@mui/material/TableRow';
import TableCell from '@mui/material/TableCell';
import ControlPointIcon from '@mui/icons-material/ControlPoint';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CloseIcon from '@mui/icons-material/Close';
import { LinearProgress, Pagination, Stack } from '@mui/material';
import { useDispatch, useSelector } from 'react-redux';
import { RootState, AppDispatch } from '../redux/store/store';
import { addWatchlistItem, removeWatchlistItem } from '../redux/slice/dashboardSlice';
import styled from '@emotion/styled';
import { StyledBox } from '../utils/ui/layout/MarginAndPadding';
import { DashboardItem } from '../types/DashboardItem';
import { FlexDivContainer } from '../utils/ui/layout/FlexDivContainer';
import { CustomTableContainer, StyledTableRow } from '../utils/ui/layout/TableLayout';
import { useNavigate } from 'react-router-dom';

interface DashboardProps {
  dashboarditem: DashboardItem;
  added: boolean;
  hovered: boolean;
}



const CompanyCell = styled(TableCell)`
  padding: 1rem;
  width: 70%;
  &:hover {
    background-color: #f0f0f0; 
  }
  cursor: pointer;
`;

const WatchlistCell = styled(TableCell)`
  padding: 1rem;
  cursor: pointer; 
  &:hover {
    background-color: #f0f0f0; 
  }
`;



const CustomTableHead = styled(TableHead)`
  border-bottom: 2px solid #000;
`;

export function Explore() {
  const reduxDispatch: AppDispatch = useDispatch();
  const tempList: DashboardProps[] = useSelector((state: RootState) =>
    state.dashboard.dashboardItems.map((item) => ({
      dashboarditem: item,
      added: false,
      hovered: false,
    })).filter(item => item.dashboarditem.stock_name !== undefined)
  );

  const loading: boolean = useSelector((state: RootState) => state.dashboard.loading);
  const watchList: DashboardItem[] = useSelector((state: RootState) => state.dashboard.watchlist);
  const [currentPage, setCurrentPage] = React.useState(1);
  const [dashboardlist, setDashboardList] = React.useState<DashboardProps[]>(tempList);
  const totalPages = Math.ceil(dashboardlist.length / 5);
  const startIndex = (currentPage - 1) * 5;
  const endIndex = startIndex + 5;
  const displayedItems = dashboardlist.slice(startIndex, endIndex);
  const navigate = useNavigate();


  React.useEffect(() => {
    if (tempList.length > 0) {
      const sortedTempList = tempList.sort((a, b) =>
        a.dashboarditem.stock_name.localeCompare(b.dashboarditem.stock_name)
      );
      setDashboardList(sortedTempList);
    }
  }, [loading]);

  React.useEffect(() => {
    const updatedDashboardList = dashboardlist.map(item => {
      const isItemInWatchlist = watchList.some(watchListItem => watchListItem.stock_symbol === item.dashboarditem.stock_symbol);
      return {
        ...item,
        added: isItemInWatchlist
      };
    });
    setDashboardList(updatedDashboardList);
  }, [watchList]);

  const toggleIcon = (index: number) => {
    const updatedDashboardList = [...dashboardlist];
    const { added, dashboarditem } = updatedDashboardList[index];

    if (added) {
      reduxDispatch(removeWatchlistItem(dashboarditem.stock_symbol));
    } else {
      reduxDispatch(addWatchlistItem(dashboarditem));
    }

    updatedDashboardList[index].added = !added;
    setDashboardList(updatedDashboardList);
  };

  const handleHover = (index: number) => {
    const updatedDashboardList = [...dashboardlist];
    updatedDashboardList[index].hovered = !updatedDashboardList[index].hovered;
    setDashboardList(updatedDashboardList);
  };

  const handleRemove = (index: number) => {
    const updatedDashboardList = [...dashboardlist];
    updatedDashboardList[index].added = false;
    setDashboardList(updatedDashboardList);
  };

  return (
    <StyledBox margin="1.25rem" padding="0.625rem" mobilemargin='1rem 0rem' mobilepadding='0rem'>
      {loading ? <LinearProgress /> : null}
      <CustomTableContainer >
        <Table>
          <CustomTableHead>
            <TableRow>
              <CompanyCell>Company</CompanyCell>
              <TableCell>Base Price (₹)</TableCell>
              <TableCell>Watchlist</TableCell>
            </TableRow>
          </CustomTableHead>
          <TableBody>
            {displayedItems.map((item, index) => (
              <StyledTableRow key={index}>
                <CompanyCell onClick={()=>navigate(`/stock/${item.dashboarditem.stock_symbol}`)}>
                  {item.dashboarditem.stock_name}</CompanyCell>
                <TableCell>{item.dashboarditem.base_price}</TableCell>
                <WatchlistCell
                  onClick={() => toggleIcon(index + startIndex)}
                >
                  {item.added ? (
                    item.hovered ? (
                      <CloseIcon
                        onMouseLeave={() => handleHover(index)}
                        sx={{ color: 'red', cursor: 'pointer' }}
                        onClick={() => handleRemove(index + startIndex)}
                      />
                    ) : (
                      <CheckCircleIcon
                        onMouseEnter={() => handleHover(index)}
                        sx={{ color: '#1871c2', cursor: 'pointer' }}
                      />
                    )
                  ) : (
                    <ControlPointIcon
                      sx={{ color: '#1871c2', cursor: 'pointer' }}
                    />
                  )}
                </WatchlistCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </CustomTableContainer>
      <StyledBox margin="1.25rem" padding="0.625rem">
        <FlexDivContainer>
        <Stack spacing={2}>
          <Pagination
            count={totalPages}
            variant="outlined"
            color="primary"
            onChange={(event, page) => setCurrentPage(page)} />
        </Stack>
        </FlexDivContainer>
      </StyledBox>
    </StyledBox>
  );
}
