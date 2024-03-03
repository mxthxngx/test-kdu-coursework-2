import { FlexDivContainer } from "../utils/ui/layout/FlexDivContainer";
import { Underline } from "../utils/ui/Underline";
import { StyledBox } from "../utils/ui/layout/MarginAndPadding";
import { useState } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store/store";
import { LinearProgress } from "@mui/material";
import { Watchlist } from "./Watchlist";
import { Explore } from "./Explore";

export function Dashboard() {
  const isLoading = useSelector((state: RootState) => state.dashboard.loading);
  const isError = useSelector((state: RootState) => state.dashboard.error);
  const [activeTab, setActiveTab] = useState('explore');

  const handleTabClick = (tab: string) => {
    if (tab !== activeTab)
      setActiveTab(tab);
  };

  const isExploreActive = activeTab === 'explore';
  const isWatchlistActive = activeTab === 'watchlist';

  const exploreTabStyle = {
    textDecoration: isExploreActive ? 'underline' : 'none',
    cursor: 'pointer'
  };

  const watchlistTabStyle = {
    textDecoration: isWatchlistActive ? 'underline' : 'none',
    cursor: 'pointer'
  };

  const exploreTabActive = activeTab === 'explore';
  const watchlistTabActive = activeTab === 'watchlist';

  let content;
  if (isLoading) {
    content = <LinearProgress />;
  } else if (isError) {
    content = <div>Error fetching data</div>;
  } else {
    content = (
      <>
        <FlexDivContainer width="10em">
          <Underline active={exploreTabActive}>
            <div onClick={() => handleTabClick('explore')} style={exploreTabStyle}>
              Explore
            </div>
          </Underline>
          <Underline active={watchlistTabActive}>
            <div onClick={() => handleTabClick('watchlist')} style={watchlistTabStyle}>
              Watchlist
            </div>
          </Underline>
        </FlexDivContainer>
        {exploreTabActive && <Explore />}
        {watchlistTabActive && <Watchlist />}
      </>
    );
  }

  return (
    <StyledBox padding="2rem" mobilepadding="1rem">
      {content}
    </StyledBox>
  );
}
