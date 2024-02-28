import React, { useContext, useEffect, useRef } from 'react';
import searchIcon from '../../assets/search-icon.svg';
import { FilteredItemsContext, SearchItemsContext, SortItems, SortItemsContext } from '../MainHome';
import { ItemsContext } from '../../App';

export function Header() {
    const { selectedFilter, setSelectedFilter } = useContext(FilteredItemsContext);
    const { items } = useContext(ItemsContext);
    const { setSearchedItem } = useContext(SearchItemsContext);
    const { selectedSortOrder, setSelectedSortOrder } = useContext(SortItemsContext);

    const categories: string[] = Array.from(new Set(items.map(item => item.category)));
    const filterOptions: string[] = categories;
    const sortOptions: SortItems[] = [SortItems.Ascending, SortItems.Descending];

    const searchInputRef = useRef<HTMLInputElement>(null);

    useEffect(() => {
        const searchQuery = searchInputRef.current?.value.toLowerCase();
        if (searchQuery !== undefined) {
            setSearchedItem(searchQuery);
        }
    }, [setSearchedItem]);

    const handleFilterChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const selectedValue = event.target.value;
        setSelectedFilter(selectedValue);
    };

    const handleSortChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        switch (event.target.value) {
            case "Ascending":
                setSelectedSortOrder(SortItems.Ascending);
                break;
            case "Descending":
                setSelectedSortOrder(SortItems.Descending);
                break;
        }
    };

    const handleSearchButtonClick = () => {
        const searchQuery = searchInputRef.current?.value.toLowerCase();
        setSearchedItem(searchQuery ?? '');
    };

    const headerStyles: { [key: string]: React.CSSProperties } = {
        header: {
            display: 'flex',
            backgroundColor: 'rgb(4, 57, 171)',
            color: 'white',
            height: '40px',
            justifyContent: 'space-between',
            alignItems: 'center',
            fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
            padding: '0 20px',
        },
        filterMain: {
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'space-evenly',
            width: '30%',
        },
        sortMain: {
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'space-evenly',
            width: '30%',
        },
        select: {
            padding: '8px',
            borderRadius: '5px',
            border: 'none',
            backgroundColor: 'white',
            color: '#333',
            cursor: 'pointer',
        },
        search: {
            width: '30%',
            display: 'flex',
            alignItems: 'center',
        },
        searchInput: {
            width: '80%',
            height: '100%',
            padding: '8px',
            border: 'none',
            borderRadius: '5px',
            marginRight: '10px',
        },
        searchButton: {
            backgroundColor: 'white',
            borderRadius: '20%',
            border: 'none',
            cursor: 'pointer',
        },
        searchIcon: {
            height: '25px',
        },
        rightHeader: {
            display: 'flex',
            justifyContent: 'space-evenly',
            width: '60%',
        },
    };

    return (
        <div style={headerStyles.header}>
            <div className="search" style={headerStyles.search}>
                <input
                    type="text"
                    placeholder="Search"
                    ref={searchInputRef}
                    style={headerStyles.searchInput}
                />
                <button onClick={handleSearchButtonClick} style={headerStyles.searchButton}>
                    <img src={searchIcon} alt="Search" className="search-icon" style={headerStyles.searchIcon} />
                </button>
            </div>

            <div className="right-header" style={headerStyles.rightHeader}>
                <div className="filter-main" style={headerStyles.filterMain}>
                    <div className="filter-title">Filter</div>
                    <div className="filter">
                        <select value={selectedFilter} onChange={handleFilterChange} style={headerStyles.select}>
                            <option value="">Select Filter</option>
                            {filterOptions.map((option) => (
                                <option key={option} value={option}>
                                    {option}
                                </option>
                            ))}
                        </select>
                    </div>
                </div>
                <div className="sort-main" style={headerStyles.sortMain}>
                    <div className="sort-title">Sort</div>
                    <div className="sort">
                        <select value={selectedSortOrder} onChange={handleSortChange} style={headerStyles.select}>
                            <option value="">Select Sort Order</option>
                            {sortOptions.map((option) => (
                                <option key={option} value={option}>
                                    {option}
                                </option>
                            ))}
                        </select>
                    </div>
                </div>
            </div>
        </div>
    );
}
