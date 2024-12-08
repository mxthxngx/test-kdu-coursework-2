import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { DashboardItem } from '../../types/DashboardItem';
import { fetchDashboardData } from '../thunk/dashboardThunk';

interface DashboardState {
  dashboardItems: DashboardItem[];
  watchlist: DashboardItem[];
  loading: boolean;
  error: string | null;
}

const initialState: DashboardState = {
  dashboardItems: [],
  watchlist: [],
  loading: false,
  error: null,
};

export const dashboardSlice = createSlice({
  name: 'dashboard',
  initialState,
  reducers: {
    setWatchlist: (state, action: PayloadAction<  DashboardItem[]>) => {
      state.watchlist = action.payload;
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
    addWatchlistItem: (state, action: PayloadAction< DashboardItem>) => {
      state.watchlist.push(action.payload);
    },
    removeWatchlistItem: (state, action: PayloadAction<string>) => {
      state.watchlist = state.watchlist.filter(
        item => item.stock_symbol !== action.payload
      );
    },
  },
  extraReducers(builder){
    builder.addCase(fetchDashboardData.fulfilled, (state, action) => {
      state.dashboardItems = action.payload;
      state.loading = false;
      state.error = null;
    }).addCase(fetchDashboardData.pending, (state) => {
      state.loading = true;
      state.error = null;
    }).addCase(fetchDashboardData.rejected, (state, action) => {
      state.loading = false;
      state.error = action.error.message as string;
    })
  }
});

export const {
  setWatchlist,
  setLoading,
  setError,
  addWatchlistItem,
  removeWatchlistItem,
} = dashboardSlice.actions;

export default dashboardSlice.reducer;
