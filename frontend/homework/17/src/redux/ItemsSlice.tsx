import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { Item, ItemsState } from '../interfaces/Item';
import { fetchItems } from './itemThunk';

const initialState: ItemsState = {
  items: [],
  loading: false,
  error: null,
};

const itemsSlice = createSlice({
  name: 'items',
  initialState,
  reducers: {},
  extraReducers(builder)  {

    builder.addCase(fetchItems.pending, (state) => {3
      state.loading = true;
      state.error = null;
    }).addCase(fetchItems.fulfilled, (state, action: PayloadAction<Item[]>) => {
      state.loading = false;
      state.items = action.payload;
      state.error = null;
    })
   
  },
});

export default itemsSlice.reducer;
