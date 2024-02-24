import React, { createContext, useContext, useEffect, useState } from 'react';
import { Header } from './header/Header';
import { Body } from './body/Body';
import { useParams } from 'react-router-dom';
import { IFilteredItems, ISearchItems, ISortItems, SortItems } from '../interfaces/Item';





export const FilteredItemsContext = createContext<IFilteredItems>({
  selectedFilter: '',
  setSelectedFilter: () => {},
});

export const SearchItemsContext = createContext<ISearchItems>({
  searchedItem: '',
  setSearchedItem: () => {},
})
export const SortItemsContext = createContext<ISortItems>({
  selectedSortOrder: SortItems.Ascending,
  setSelectedSortOrder: () => {},
})

export function MainHome() {
  const [selectedFilter, setSelectedFilter] = useState('');
  const [searchedItem, setSearchedItem] = useState('');
 const [selectedSortOrder, setSelectedSortOrder] = useState(SortItems.Ascending);


  return (
    <FilteredItemsContext.Provider value={{ selectedFilter: selectedFilter, setSelectedFilter: setSelectedFilter }}>
      <SearchItemsContext.Provider value={{searchedItem:searchedItem,setSearchedItem:setSearchedItem}}>
        <SortItemsContext.Provider value = {{selectedSortOrder:selectedSortOrder,setSelectedSortOrder:setSelectedSortOrder}}>
      <div>
        <Header />
        <Body />
      </div>
      </SortItemsContext.Provider>
      </SearchItemsContext.Provider>
    </FilteredItemsContext.Provider>
  );
}
export { SortItems };

