export interface Item {
    id: number;
    title: string;
    price: number;
    description: string;
    category: string;
    image: string;
    rating: {
      rate: number;
      count: number;
    };
  }

  export interface ItemParams {
    id: string;
    [key: string]: string | undefined;
}

export interface IFilteredItems {
    selectedFilter: string;
    setSelectedFilter: React.Dispatch<React.SetStateAction<string>>;
  }


export enum SortItems {
    Ascending = 'Ascending',
    Descending = 'Descending',
  }
export  interface ISortItems 
  {
    selectedSortOrder:SortItems
    setSelectedSortOrder:React.Dispatch<React.SetStateAction<SortItems>>
  }
 export interface ISearchItems 
  {
    searchedItem:string,
    setSearchedItem:React.Dispatch<React.SetStateAction<string>>
  }