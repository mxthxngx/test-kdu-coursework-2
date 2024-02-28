import  { createContext,  useMemo,  useState } from 'react';
import { Header } from './header/Header';
import { Body } from './body/Body';
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

 const contextValue = useMemo(() => {
  return { selectedFilter, setSelectedFilter };
}, [selectedFilter, setSelectedFilter]);

const contextValue2 = useMemo(() => {
  return { searchedItem, setSearchedItem };
}, [searchedItem, setSearchedItem]);

const contextValue3 = useMemo(() => {
  return { selectedSortOrder, setSelectedSortOrder };
}, [selectedSortOrder, setSelectedSortOrder]);
  return (
    <FilteredItemsContext.Provider value={contextValue}>
      <SearchItemsContext.Provider value={contextValue2}>
        <SortItemsContext.Provider value = {contextValue3}>
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

