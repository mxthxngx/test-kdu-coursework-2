import { createAsyncThunk } from '@reduxjs/toolkit';
import { Item } from '../interfaces/Item';

export const fetchItems = createAsyncThunk<Item[]>('items/fetchItems', async () => {
  try {
    const response = await fetch('https://fakestoreapi.com/products');
    if (!response.ok) {
        console.log("failed to fetch items");
      throw new Error('Failed to fetch items');
    }
    const data: Item[] = await response.json();
    console.log(data)
    return data;
  } catch (error) {
    throw new Error('Error fetching items: ' + (error as Error).message);
  }
});
