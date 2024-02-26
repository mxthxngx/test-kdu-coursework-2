import React from 'react';
import { useDispatch  } from 'react-redux';
import { setInputItem } from '../redux/ItemSlice';
export function Header() {
    const reduxDispatch = useDispatch();
    /**
     * Sets the item list handler.
     *
     * @param {string} input - the input string to set
     * @return {void} 
     */
    const setItemListHandler = (input:string) => {
        reduxDispatch(setInputItem(input))
    }
    /**
     * A function that handles the search value change event.
     *
     * @param {React.ChangeEvent<HTMLInputElement>} event - the input change event
     * @return {void} 
     */


    const searchValue = (event: React.ChangeEvent<HTMLInputElement>) => {

        setItemListHandler(event.target.value);
    }
    const cssStyles: {[key: string]: React.CSSProperties} = {
        heading: {
            margin: '0 auto',
            backgroundColor: 'rgb(55, 163, 55)',
            display: 'flex',
            justifyContent: 'space-evenly',
            height: '100px',
            alignItems: 'center',
            color: 'white'
        },
        headingTitle: {
            width: '70%'
        },
        searchBtn: {
            height: '30px',
            borderRadius: '5px',
            border: 'none',
            padding: '10px'
        }
    };
    return (
        <div style={cssStyles.heading}>
            <div style={cssStyles.headingTitle}>
                <h1>Item Lister</h1>
            </div>
            <input type="search" placeholder='Search items' style={cssStyles.searchBtn} onChange={searchValue} />
        </div>
    )
}
