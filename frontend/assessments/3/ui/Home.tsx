import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../redux/Store";
import { Addons } from "./Addons";
import { DateSelection } from "./DateSelection";
import { Room } from "./RoomType";
import { useEffect } from "react";
import { fetchRoomData } from "../redux/Thunk";
import { Price } from "./Price";

export function Home() {
    const dispatch: AppDispatch = useDispatch();
    const [submittedValues, setSubmittedValues] = useState<any>(null); // State to store submitted values

    useEffect(() => {
        try {
            dispatch(fetchRoomData());
        } catch (error) {
            console.error(error);
        }
    }, []);

    const roomItems = useSelector((state: RootState) => state.rooms.roomItems);
    const currentlySelectedRoom = useSelector((state: RootState) => state.rooms.currentlySelectedRoom);
    const addonsSelected = useSelector((state: RootState) => state.rooms.addonsSelected);
    const dates = useSelector((state: RootState) => state.rooms.dates);

    const handleSubmit = () => {
        if (currentlySelectedRoom && addonsSelected.length > 0 && dates !== 0) {
            setSubmittedValues({
                room: currentlySelectedRoom,
                addons: addonsSelected,
                dates: dates
            });
        } else {
            alert("Please fill all the fields before submitting.");
        }
    };

    return (
        <div>
            <Room />
            <DateSelection />
            <Addons />
            <Price />
            <button onClick={handleSubmit}>Submit</button>

            {submittedValues && (
                <div>
                    <h2>Submitted Values</h2>
                    <p>Room: {submittedValues.room.name}</p>
                    <p>Addons:</p>
                    <ul>
                        {submittedValues.addons.map((addon: any, index: number) => (
                            <li key={index}>{addon.name}</li>
                        ))}
                    </ul>
                    <p>Dates: {submittedValues.dates}</p>
                </div>
            )}
        </div>
    );
}
