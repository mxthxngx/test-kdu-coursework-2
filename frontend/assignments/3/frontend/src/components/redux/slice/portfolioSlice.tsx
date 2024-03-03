import { PortfolioItem } from "../../types/PortfolioItem";
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { fetchPortfolioData } from "../thunk/portfolioThunk";
interface  PortfolioState {
    portfolioItems: PortfolioItem[];
    showFilters:boolean;
    loading: boolean;
    error: string | null;
}

const initialState: PortfolioState = {
    portfolioItems: [],
    loading: false,
    showFilters: false,
    error: null,
};

export const portfolioSlice = createSlice({
    name: 'portfolio',
    initialState,
    reducers: {
        setPortfolio: (state, action: PayloadAction<PortfolioItem[]>) => {
            state.portfolioItems = action.payload;
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
        addPortfolioItem: (state, action: PayloadAction<PortfolioItem>) => {
            state.portfolioItems.push(action.payload);
        },
        setFilter: (state, action: PayloadAction<boolean>) => {
            state.showFilters = action.payload;
        }
    },
    extraReducers(builder){
        builder.addCase(fetchPortfolioData.fulfilled, (state, action) => {
            state.portfolioItems = action.payload;
            state.loading = false;
            state.error = null;
        }).addCase(fetchPortfolioData.pending, (state) => {
            state.loading = true;
            state.error = null;
        }).addCase(fetchPortfolioData.rejected, (state, action) => {
            state.loading = false;
            state.error = action.error.message as string;
        })
    }
}
)

export const {
    setPortfolio,
    setLoading,
    setError,
    addPortfolioItem,
    setFilter
} = portfolioSlice.actions;

export default portfolioSlice.reducer