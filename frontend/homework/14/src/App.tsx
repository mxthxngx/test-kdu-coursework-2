import React, { createContext, useState } from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { MainHome } from './homepage/MainHome';
import { ItemPage } from './homepage/Item/ItemPage';
import { Item } from './interfaces/Item';

interface ItemsContextInterface 
{
  items: Item[];
  setItems: React.Dispatch<React.SetStateAction<Item[]>>;
}

export const ItemsContext = createContext<ItemsContextInterface>({
  items: [],
  setItems: () => {},
});

function App() {
  const [items, setItems] = useState<Item[]>([]);

  return (
    <BrowserRouter>
      <ItemsContext.Provider value={{ items, setItems }}>
        <Routes>
        <Route path="/:category" element={<MainHome />} />
          <Route path='/' element={<MainHome />} />
          <Route path="/ItemPage/:id" element={<ItemPage />} />
        </Routes>
      </ItemsContext.Provider>
    </BrowserRouter>
  );
}

export default App;
