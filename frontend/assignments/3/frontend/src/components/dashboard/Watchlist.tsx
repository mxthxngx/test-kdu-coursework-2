import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '../redux/store/store';
import { DashboardItem } from '../types/DashboardItem';
import styled from '@emotion/styled';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CloseIcon from '@mui/icons-material/Close';
import { Pagination, Stack } from '@mui/material';
import { removeWatchlistItem } from '../redux/slice/dashboardSlice';
import { StyledBox } from '../utils/ui/layout/MarginAndPadding';
import * as React from 'react';
import { FlexDivContainer } from '../utils/ui/layout/FlexDivContainer';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableRow from '@mui/material/TableRow';
import TableCell from '@mui/material/TableCell';
import { CustomTableContainer, CustomTableHead } from '../utils/ui/layout/TableLayout';
import { useNavigate } from 'react-router-dom';


const StyledTableRow = styled(TableRow)`
  border-bottom: 1px solid #000;
`;

const StyledTableCell = styled(TableCell)`
  padding: 1rem;
  cursor: pointer;

  &:hover {
    background-color: #f0f0f0; 
  }
`;



export function Watchlist() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const watchlist: DashboardItem[] = useSelector((state: RootState) => state.dashboard.watchlist);
  const [hoveredIndex, setHoveredIndex] = React.useState<number | null>(null); 
  const [currentPage, setCurrentPage] = React.useState(1); 
  const itemsPerPage = 5; 

  /**
   * Handles the hover event for the given index.
   *
   * @param {number | null} index - The index to be hovered over
   * @return {void} 
   */
  const handleHover = (index: number | null) => {
    setHoveredIndex(index); 
  };

  /**
   * Handles the removal of an item.
   *
   * @param {string} stockSymbol - the stock symbol to be removed
   * @return {void} 
   */
  const handleRemoveItem = (stockSymbol: string) => {
    dispatch(removeWatchlistItem(stockSymbol));
  };

  const startIndex = (currentPage - 1) * itemsPerPage;
  const endIndex = Math.min(startIndex + itemsPerPage, watchlist.length);
  const currentPageItems = watchlist.slice(startIndex, endIndex);

  const totalPages = Math.ceil(watchlist.length / itemsPerPage);

  return (
    <StyledBox margin="1.25rem" padding="0.625rem" mobilemargin='1rem 0rem' mobilepadding='0rem'>
      <CustomTableContainer >
        <Table>
          <CustomTableHead>
            <TableRow>
              <StyledTableCell>Company</StyledTableCell>
              <StyledTableCell>Base Price (₹)</StyledTableCell>
              <StyledTableCell>Watchlist</StyledTableCell>
            </TableRow>
          </CustomTableHead>
          <TableBody>
            {currentPageItems.map((item, index) => (
              <StyledTableRow key={index}>
                <StyledTableCell onClick={()=>navigate(`/stock/${item.stock_symbol}`)}>{item.stock_name}</StyledTableCell>
                <StyledTableCell>{item.base_price}</StyledTableCell>
                <StyledTableCell
                  onMouseEnter={() => handleHover(startIndex + index)}
                  onMouseLeave={() => handleHover(null)} 
                  onClick={() => handleRemoveItem(item.stock_symbol)}
                >
                  {hoveredIndex === startIndex + index ? ( 
                    <CloseIcon sx={{ color: 'red', cursor: 'pointer' }} />
                  ) : (
                    <CheckCircleIcon sx={{ color: '#1871c2', cursor: 'pointer' }} />
                  )}
                </StyledTableCell>
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
