import { createAsyncThunk } from "@reduxjs/toolkit";
import { DashboardItem } from "../../types/DashboardItem";

export const fetchDashboardData = createAsyncThunk<DashboardItem[]>(
  "dashboardItems",
  async () => {
    try {
      const response = await fetch('http://localhost:3000/dashboard/explore');
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data: DashboardItem[] = await response.json();
      console.log("inside thunk:",data)
      return data;
    } catch (error) {
      console.error(error);
      throw new Error("Error fetching dashboard data: " + (error as Error).message);
    }
  }
);

