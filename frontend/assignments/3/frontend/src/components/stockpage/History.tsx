import React from 'react';
import { AppDispatch, RootState } from "../redux/store/store";
import { useDispatch, useSelector } from 'react-redux';
import Container from '@mui/material/Container';
import { Box, Typography } from '@mui/material';
import { HistoryItem, TransactionType } from '../types/HistoryItem';
import { FlexDivContainer } from '../utils/ui/layout/FlexDivContainer';
export function History() {
    const dispatch: AppDispatch = useDispatch();
    const history = useSelector((state: RootState) => state.price.history);

    const formatDate = (dateString: string) => {
        const options = { weekday: 'long', month: 'short', day: '2-digit', year: 'numeric', hour: '2-digit', minute: '2-digit' };
        const formattedDate = new Date(dateString).toLocaleDateString('en-US', options);
        return formattedDate;
    };

    return (
        <Container maxWidth="sm" style={{ border: '1px solid black', overflowY: 'scroll', margin: '20px', width: '80%', height: '40vh', borderRadius: '1rem', padding: '8px' }}>
            <Typography variant="h6">History</Typography>
            {history.map((item: HistoryItem) => (
                <Box
                    key={item.date}
                    sx={{
                        display: 'flex',
                        flexDirection: 'row',
                        alignItems: 'center',
                        justifyContent: 'space-between',
                        border: '1px solid black',
                        borderRadius: '4px',
                        padding: '8px',
                        margin: '8px',
                    }}
                >
                    <FlexDivContainer justifyCon='space-around' width='100%'>
                        <div>
                            <Typography variant="body1"> {item.quantity} Stocks</Typography>
                            <Typography variant="body1">{formatDate(item.date)}</Typography>
                        </div>
                        <div>
                            <Typography variant="body1" style={{ color: item.type === TransactionType.BUY ? '#2F9E44' : '#E03131' }}>{item.type}</Typography>
                        </div>
                    </FlexDivContainer>
                </Box>
            ))}
        </Container>
    );
}

