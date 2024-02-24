import React, { useContext, useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { ItemsContext } from '../../App';
import { FilteredItemsContext, SearchItemsContext, SortItems, SortItemsContext } from '../MainHome';
import { Item } from '../../interfaces/Item';

export function Body() {
    const { items, setItems } = useContext(ItemsContext);
    useEffect(() => {
        fetchItems();
    }, []);
    const [filteredItems, setFilteredItems] = useState<Item[]>([]);
    const { searchedItem, setSearchedItem } = useContext(SearchItemsContext);
    const { selectedFilter, setSelectedFilter } = useContext(FilteredItemsContext);
    const { selectedSortOrder, setSelectedSortOrder } = useContext(SortItemsContext);
    const { category } = useParams<{ category: string }>();

    useEffect(() => {
        let filteredByCategory = category ? items.filter(item => item.category === category) : items;
        filteredByCategory = selectedFilter
            ? filteredByCategory.filter(item => item.category === selectedFilter)
            : filteredByCategory;
        const filteredBySearch = searchedItem
            ? filteredByCategory.filter(item =>
                item.title.toLowerCase().includes(searchedItem.toLowerCase())
            )
            : filteredByCategory;

        const sortedItems = selectedSortOrder === SortItems.Ascending
            ? [...filteredBySearch].sort((a, b) => a.price - b.price)
            : [...filteredBySearch].sort((a, b) => b.price - a.price);

        setFilteredItems(sortedItems);
    }, [selectedFilter, searchedItem, items, selectedSortOrder]);


    const fetchItems = async () => {
        try {
            const response = await fetch('https://fakestoreapi.com/products');
            if (!response.ok) {
                throw new Error('Failed to fetch items');
            }
            const data = await response.json();
            setItems(data);
            setFilteredItems(data);
        } catch (error) {
            console.error('Error fetching items:', error);
        }
    };

    const itemStyle: { [key: string]: React.CSSProperties } = {
        itemList: {
            display: 'flex',
            flexWrap: 'wrap',
            justifyContent: 'center',
        },
        itemContainer: {
            width: '20%',
            padding: '10px',
        },
        item: {
            border: '1px solid #ccc',
            padding: '10px',
            display: 'flex',
            backgroundColor: 'white',
            flexDirection: 'column',
            justifyContent: 'center',
            alignItems: 'center',
            borderRadius: '10px',
            boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
            transition: 'all 0.3s ease',
            height: '30vh',
        },
        itemHover: {
            transform: 'translateY(-5px)',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)',
        },
        itemImage: {
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            height: '200px',
            width: '200px',
            borderRadius: '10%',
            objectFit: 'cover',
        },
        itemLink: {
            textDecoration: 'none',
            color: 'inherit',
        },
        itemDetails: {
            textAlign: 'left',
        },
        itemDetailsH3: {
            fontSize: '18px',
            marginBottom: '5px',
        },
        itemDetailsP: {
            fontSize: '14px',
            marginBottom: '3px',
        }
    };

    return (
        <div>
            <div style={itemStyle.itemList}>
                {filteredItems.map(item => (
                    <div style={itemStyle.itemContainer} key={item.id}>
                        <Link to={`/ItemPage/${item.id}`} style={itemStyle.itemLink}>
                            <div style={itemStyle.item} className="item">
                                <img src={item.image} alt={item.title} style={itemStyle.itemImage} className='item-image' />
                                <div style={itemStyle.itemDetails} className="item-details">
                                    <h3 style={itemStyle.itemDetailsH3}>{item.title}</h3>
                                    <p style={itemStyle.itemDetailsP}>${item.price}</p>
                                </div>
                            </div>
                        </Link>
                    </div>
                ))}
            </div>
        </div>
    );
}
