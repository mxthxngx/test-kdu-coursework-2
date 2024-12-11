import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { HistoryItem } from '../../types/HistoryItem';

interface PriceState {
    history: HistoryItem[];
    loading: boolean;
    error: string | null;
}

const initialState: PriceState = {
    history: [],
    loading: false,
    error: null,
};

export const priceSlice = createSlice({
    name: 'price',
    initialState,
    reducers: {
        sethistory: (state, action: PayloadAction<HistoryItem>) => {
            state.history.push(action.payload);
            state.loading = false;
            state.error = null;
        },
     
        setLoading: (state, action: PayloadAction<boolean>) => {
            state.loading = action.payload;
        },
        setError: (state, action: PayloadAction<string>) => {
            state.error = action.payload;
            state.loading = false;
        },
        clearhistory: (state) => {
            state.history = [];
            state.loading = false;
            state.error = null;
        },
    },
});

export const { sethistory, setLoading, setError, clearhistory } = priceSlice.actions;

export default priceSlice.reducer;
