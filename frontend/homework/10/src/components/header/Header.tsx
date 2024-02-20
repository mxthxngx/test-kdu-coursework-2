import React from 'react';
import "./style.css";

export function Header({ setInputItem }: {  readonly setInputItem: React.Dispatch<React.SetStateAction<string>> }) {
    const searchValue = (event: React.ChangeEvent<HTMLInputElement>) => {
        setInputItem(event.target.value);
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
