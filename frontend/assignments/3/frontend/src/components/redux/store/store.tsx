import {  configureStore } from "@reduxjs/toolkit";
import { dashboardSlice } from "../slice/dashboardSlice";
import {priceSlice} from "../slice/priceSlice";
import {userSlice}from "../slice/userSlice";
import { portfolioSlice }from "../slice/portfolioSlice";
export const Store = configureStore({
    reducer:{
        dashboard: dashboardSlice.reducer,
        price:priceSlice.reducer,
        user:userSlice.reducer,
        portfolio:portfolioSlice.reducer
    },
});
export type AppDispatch = typeof Store.dispatch;
export type RootState=ReturnType<typeof Store.getState>;