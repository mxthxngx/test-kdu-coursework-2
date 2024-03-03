import React, { useEffect, useState } from 'react';
import Container from '@mui/material/Container';
import { Box, Typography } from '@mui/material';
import { FlexDivContainer } from '../utils/ui/layout/FlexDivContainer';
import { SocketProps } from "../types/SocketProps";
import { HistoryItem, TransactionType } from '../types/HistoryItem';

export function GlobalHistory({ socket }: SocketProps) {
    const [transactions, setTransactions] = useState<HistoryItem[]>([]);
    const [username, setUsername] = useState<string>('');
    const [symbol, setSymbol] = useState<string>('');
    useEffect(() => {
        socket?.on('global', (username: string, data: any,symbol:string) => {
            const transaction = data as HistoryItem;
            setUsername(username);
            setSymbol(symbol);
            if (transaction) {
                setTransactions(prevTransactions => [...prevTransactions, transaction]);
            }
        });

        return () => {
            socket?.off('global');
        };
    }, [socket]);

    return (
        <Container maxWidth="sm" style={{ border: '1px solid black', overflowY: 'scroll', margin: '20px', width: '80%', height: '40vh', borderRadius: '1rem', padding: '8px' }}>
            <Typography variant="h6">Global Transaction History</Typography>
            {transactions.map((transaction, index) => (
                <div key={index}>
                    <FlexDivContainer justifyCon="space-between">
                        <Box
                            key={transaction.date}
                            sx={{
                                display: 'flex',
                                flexDirection: 'column',
                                alignItems: 'flex-start',
                                justifyContent: 'space-between',
                                border: '1px solid black',
                                borderRadius: '1rem',
                                padding: '8px',
                                width:'100%',
                                margin: '8px',
                            }}
                        >
                            <Typography variant="body1">
                                {username} {transaction.type === TransactionType.BUY ? 'bought' : 'sold'} {transaction.quantity} shares of {symbol}
                            </Typography>
                            <div>
                                <Typography variant="body1">{new Date(transaction.date).toLocaleTimeString()}</Typography>
                            </div>
                        </Box>
                    </FlexDivContainer>
                </div>
            ))}
        </Container>
    );
}
