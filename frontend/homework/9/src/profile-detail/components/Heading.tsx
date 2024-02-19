
interface HeadingProps {
  readonly name: string;
  readonly firstname: string;
  readonly qualification: string;
}

export function Heading({ name, firstname, qualification }: HeadingProps) {
  return (
    <div>
      <h1>{name}</h1>
      <h3>{firstname}</h3>
      <h2>{qualification}</h2>
    </div>
  );
}
