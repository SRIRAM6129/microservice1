import { Outlet } from "react-router-dom";
import StaffDetails from "./Components/StaffDetails";

const MainPage = () => {
  return (
    <div className="h-[calc(100%-4rem)] ml-[250px] mt-[4rem]  p-4">
      <Outlet />
    </div>
  );
};

export default MainPage;
