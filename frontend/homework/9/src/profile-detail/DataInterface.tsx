export interface DataInterface 
{
    name: string,
    firstname: string,
    qualification: string,
    skills:SkillsInterface[],
    hobbies: HobbiesInterface[]
}
export interface SkillsInterface 
{
    id:number,
    skill: string
}
export interface HobbiesInterface
{
    id:number,
    hobby:string
}