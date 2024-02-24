import React, { useContext } from 'react';
import { useParams } from 'react-router-dom';
import { ItemsContext } from '../../App';
import searchIcon from '../../assets/search-icon.svg';
import { useNavigate } from 'react-router-dom';
import {ItemParams} from '../../interfaces/Item'

export function ItemPage() {
    const { id } = useParams<ItemParams>();
    const { items } = useContext(ItemsContext);
    const item = items.find(item => item.id === parseInt(id!, 10));

    if (!item) {
        return <div>Item not found</div>;
    }

    let navigate = useNavigate();
    const handleBack = () => {
        navigate(-1);
    };

    const itemPageStyles: { [key: string]: React.CSSProperties } = {
        body: {
            backgroundColor: 'rgb(236, 236, 236)',
            margin: 0,
            padding: 0,
            fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
        },
        itemTitle: {
            fontSize: '28px',
            fontWeight: 'bold',
            marginBottom: '10px',
        },
        productDescriptionTitle: {
            fontSize: '24px',
            fontWeight: 'bold',
            marginBottom: '10px',
        },
        itemRightContainer: {
            display: 'flex',
            flexDirection: 'column',
            justifyContent: 'space-evenly',
            fontSize: '20px',
            width: '40vw',
            height: '40vh',
        },
        itemPageContainer: {
            height: '80vh',
            backgroundColor: 'rgb(236, 236, 236)',
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            padding: '20px',
            justifyContent: 'space-evenly',
        },
        itemImage: {
            height: '40vh',
            width: '40vw',
            objectFit: 'contain',
        },
        itemDetailsItemPage: {
            display: 'flex',
            justifyContent: 'space-evenly',
            width: '80vw',
            maxHeight: '90vh',
            alignItems: 'center',
        },
        bold: {
            fontWeight: 'bold',
        },
        backToHomeBtn: {
            backgroundColor: '#007bff',
            color: 'white',
            border: 'none',
            borderRadius: '10px',
            padding: '10px',
            fontSize: '16px',
            cursor: 'pointer',
        },
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
        select: {
            padding: '8px',
            borderRadius: '5px',
            border: 'none',
            backgroundColor: 'white',
            color: '#333',
            cursor: 'pointer',
        },
    };
    
    return (
        <div style={itemPageStyles.body}>
            <div className='item-container-itempage'>
                <div className='header' style={headerStyles.header}>
                    <div className='search' style={headerStyles.search}>
                        <input type='text' placeholder='Search'   style={headerStyles.searchInput}/>
                        <button>
                            <img src={searchIcon} alt='Search' className='search-icon' style={headerStyles.searchIcon}  />
                        </button>
                    </div>
                    <div className='right-header'></div>
                </div>
                <div style={itemPageStyles.itemPageContainer}>
                    <p style={itemPageStyles.itemTitle}>{item.title}</p>
                    <div style={itemPageStyles.itemDetailsItemPage}>
                        <div className='item-image-container'>
                            <img src={item.image} alt={item.title} style={itemPageStyles.itemImage} />
                        </div>
                        <div style={itemPageStyles.itemRightContainer}>
                            <p style={itemPageStyles.productDescriptionTitle}>Product Description</p>
                            <p>{item.description}</p>
                            <p>
                                <span style={itemPageStyles.bold}>Price:</span> ${item.price}
                            </p>
                            <p>
                                <span style={itemPageStyles.bold}>Category: </span>
                                {item.category}
                            </p>
                            <p>
                                <span style={itemPageStyles.bold}>Rating:</span> {item.rating.rate} (
                                {item.rating.count} reviews)
                            </p>
                            <button style={itemPageStyles.backToHomeBtn} onClick={handleBack}>
                                Back to Home
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
