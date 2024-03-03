import { useEffect, useState } from 'react';

export const useMediaQuery = (query: string): boolean => {
  const mediaMatch = window.matchMedia(query);
  const [matches, setMatches] = useState<boolean>(mediaMatch.matches);

  useEffect(() => {
    const handler = (e: MediaQueryListEvent) => setMatches(e.matches);
    mediaMatch.addEventListener('change', handler);
    return () => mediaMatch.removeEventListener('change', handler);
  }, [mediaMatch]);

  return matches;
};
