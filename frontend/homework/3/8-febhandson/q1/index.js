/**
 * Calculate tips and final amounts for a list of bills.
 *
 * @param {array} bills - An array of bill amounts
 * @return {array} An array containing two arrays: tips and final amounts
 */
function tipCalculator(bills) {
    let tips = [];
    let finalAmounts = [];

    for (let i = 0; i < bills.length; i++) {
        let bill = bills[i];
        let tip;

        if (bill < 50) {
            tip = bill * 0.20;
        } else if (bill >= 50 && bill <= 200) {
            tip = bill * 0.15;
        } else {
            tip = bill * 0.10;
        }

        tips.push(tip);
        finalAmounts.push(bill + tip);
    }

    return [tips, finalAmounts];
}


const bills = [140, 45, 280];

const [tips, finalAmounts] = tipCalculator(bills);

console.log("Tips:", tips);
console.log("Final paid amounts:", finalAmounts);
