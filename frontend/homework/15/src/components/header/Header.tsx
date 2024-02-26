import React from 'react';
import "./style.css";
import { useDispatch  } from 'react-redux';
import { setInputItem } from '../redux/ItemSlice';
export function Header() {
    const reduxDispatch = useDispatch();
    const setItemListHandler = (input:string) => {
        reduxDispatch(setInputItem(input))
    }
    const searchValue = (event: React.ChangeEvent<HTMLInputElement>) => {

        setItemListHandler(event.target.value);
    }

    return (
        <div className="heading">
            <div className="heading-title">
                <h1>Item Lister</h1>
            </div>
            <input type="search" placeholder='Search items' className='search-btn' onChange={searchValue} />
        </div>
    )
}
