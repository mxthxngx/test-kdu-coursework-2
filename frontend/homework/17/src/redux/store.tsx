import { configureStore } from '@reduxjs/toolkit';
import itemsReducer from './ItemsSlice';
import errorReducer from './SnackbarSlice';
const store = configureStore({
  reducer: {
    items: itemsReducer,
    error:errorReducer
  },
});

export default store;

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
