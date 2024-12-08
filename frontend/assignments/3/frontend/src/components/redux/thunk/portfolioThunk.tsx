import { createAsyncThunk } from "@reduxjs/toolkit";
import { PortfolioItem } from "../../types/PortfolioItem";

export const fetchPortfolioData = createAsyncThunk<PortfolioItem[], string>(
  "portfolio/fetchPortfolioData",
  async (username: string) => {
    try {
      console.log("username: ", username);
      const response = await fetch(`http://localhost:3000/portfolio/get/${username}`);
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data: { transactionData: PortfolioItem[] } = await response.json();
      
      return data.transactionData;
    } catch (error) {
      console.error(error);
      throw new Error("Error fetching portfolio data: " + (error as Error).message);
    }
  }
);
