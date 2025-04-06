import { useState } from "react";
import StaffDetails from "./Components/StaffDetails";


const Staff = () => {

  const [staffDetails, setstaffDetails] = useState({});

  const getDetails = async (classId) => {
    try {
      const details = await axios.get(`http://localhost:8080/STAFF-SERVICE/${classId}`);
      if (details != null) {
        setstaffDetails(details);
      }
    } catch (error) {
      console.log(error);
    }

  }

  return (
    <div className="">
      <div className="overflow-x-auto  border-2 border-gray-200">
        <table className="min-w-full ">
          <thead className="w-full text-black border-b-2 border-gray-200 h-[50px]">
            <tr>
              <th className=""> S.NO </th>
              <th className="border-l-2 border-gray-200"> Name </th>
              <th className="border-l-2 border-gray-200"> Department </th>
              <th className="border-l-2 border-gray-200"> Contact </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>STAFF1</td>
              <td>STAFF1</td>
              <td>STAFF1</td>
              <td>STAFF1</td>
            </tr>
          </tbody>
        </table>
      </div>
      <StaffDetails />
    </div>
  );
};

export default Staff;
