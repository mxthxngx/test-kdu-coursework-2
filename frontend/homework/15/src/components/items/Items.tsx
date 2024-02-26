import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../redux/Store';
import { TodoInterface } from '../../TodoInterface';
import { setItemList} from '../redux/ItemSlice';

export function Items() {
  const reduxDispatch = useDispatch();
  const itemList = useSelector((state: RootState) => state.adder.itemList);
  const setItemListHandler = (itemList: TodoInterface[]) => {
    reduxDispatch(setItemList(itemList));
  };
  const { itemInput } = useSelector((state: RootState) => state.adder);
  const handleDelete = (id: number) => {
    setItemListHandler(itemList.filter((item) => item.id !== id));
  };


  const toggleItemStrike = (id: number) => {
    const updatedItemList = itemList.map((item) =>
      item.id === id ? { ...item, isStriked: !item.isStriked } : item
    );
    setItemListHandler(updatedItemList);
  };

  const clearAllStrikedItems = () => {
    const updatedItemList = itemList.filter((item) => !item.isStriked);
    setItemListHandler(updatedItemList);
  };

  let filteredItems = itemList;
  if (itemInput.trim() !== '') {
    filteredItems = itemList.filter((item) => item.todo.toLowerCase().includes(itemInput.toLowerCase()));
  }

  const cssStyles: { [key: string]: React.CSSProperties } = {
    itemContainer: {
      textAlign: 'center',
      marginTop: '20px',
    },
    noItems: {
      color: 'gray',
    },
    todoItem: {
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'space-between',
      border: '1px solid gainsboro',
      borderRadius: '2px',
      padding: '10px',
      marginTop: '10px',
    },
    li: {
      listStyle: 'none',
      textDecoration: 'line-through', 
    },
    crossBtn: {
      padding: '5px',
      borderRadius: '5px',
      backgroundColor: 'red',
      color: 'white',
      border: 'none',
      cursor: 'pointer',
    },
    clearBtn: {
      padding: '8px 16px',
      borderRadius: '5px',
      backgroundColor: '#f44336',
      color: 'white',
      border: 'none',
      cursor: 'pointer',
      marginTop: '10px',
      outline: 'none',
    },
    ul: {
      margin: '0',
      padding: '0',
    },
    inputBox: {
      outline: 'none',
      border:'none'
    }
  };
  const handleItemChange = (id: number, newValue: string) => {
    const updatedItemList = itemList.map((item) =>
      item.id === id ? { ...item, todo: newValue } : item
    );
    setItemListHandler(updatedItemList);
  };

  return (
    <div style={cssStyles.itemContainer} className="item-container">
      <h2>Items</h2>
      <button style={cssStyles.clearBtn} onClick={clearAllStrikedItems}>Clear All Striked</button>
      {filteredItems.length === 0 ? (
        <p style={cssStyles.noItems}>No Items found</p>
      ) : (
        <ul style={cssStyles.ul}>
          {filteredItems.map((item) => (
            <div style={cssStyles.todoItem} key={item.id}>
             
              <label style={{ textDecoration: item.isStriked ? 'line-through' : 'none' ,display:'flex',alignItems:'center'}}>
                <input
                  type="checkbox"
                  checked={item.isStriked}
                  onChange={() => toggleItemStrike(item.id)}
                />
                {item.isStriked?(
                  <div>{item.todo}</div>
                ):(
                  <input
                    style={{ ...cssStyles.inputBox }}
                    type="text"
                    value={item.todo}
                    onChange={(e) => handleItemChange(item.id, e.target.value)}
                  />
                )}
               
              </label>
              <button style={cssStyles.crossBtn} onClick={() => handleDelete(item.id)}>
                x
              </button>
            </div>
          ))}
        </ul>
      )}
    </div>
  );
}
