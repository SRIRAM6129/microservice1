const Sidebar = () => {
  return (
    <div className="fixed top-0 left-0 h-screen w-[250px] bg-white text-white flex justify-center  flex-col shadow-2xl ">
      <div className=" flex justify-center items-center px-4 py-4">
      </div>
      <ul className="flex-1  bg-sidebarColor h-[90%]  flex flex-col gap-2 items-end py-4">

        <li className="w-full"><input type="text" className="h-10  w-full bg-gray-200 " placeholder="Search..." /></li>

        <li className="w-[90%] h-[40px]  pl-4 rounded-l-full hover:bg-gray-200 group">
          <a href="/dashboard" className=" h-full w-full  flex items-center   justify-start font-sans text-[18px] group-hover:text-black ">
            Dashboard
          </a>
        </li>
        <li className="w-[90%] h-[40px]  pl-4 rounded-l-full hover:bg-gray-200 group">
          <a href="/timetable" className=" h-full w-full  flex items-center   justify-start font-sans text-[18px] group-hover:text-black ">
            My Time Table
          </a>
        </li>
        <li className="w-[90%] h-[40px]  pl-4 rounded-l-full hover:bg-gray-200 group">
          <a href="/subject_registration" className=" h-full w-full  flex items-center   justify-start font-sans text-[18px] group-hover:text-black ">
            Subject Registration
          </a>
        </li>
        <li className="w-[90%] h-[40px]  pl-4 rounded-l-full hover:bg-gray-200 group">
          <a href="/apply_leave" className=" h-full w-full  flex items-center   justify-start font-sans text-[18px] group-hover:text-black ">
            Apply Leave/OD
          </a>
        </li>
        <li className="w-[90%] h-[40px]  pl-4 rounded-l-full hover:bg-gray-200 group">
          <a href="/attendance" className=" h-full w-full  flex items-center   justify-start font-sans text-[18px] group-hover:text-black ">
            Attendance
          </a>
        </li>
        <li className="w-[90%] h-[40px]  pl-4 rounded-l-full hover:bg-gray-200 group">
          <a href="/staff" className=" h-full w-full  flex items-center   justify-start font-sans text-[18px] group-hover:text-black ">
            Staff
          </a>
        </li>
        <li className="w-[90%] h-[40px]  pl-4 rounded-l-full hover:bg-gray-200 group">
          <a href="/cat_marks" className=" h-full w-full  flex items-center   justify-start font-sans text-[18px] group-hover:text-black ">
            Cat Marks
          </a>
        </li>
        <li className="w-[90%] h-[40px]  pl-4 rounded-l-full hover:bg-gray-200 group">
          <a href="/assisgnment_marks" className=" h-full w-full  flex items-center   justify-start font-sans text-[18px] group-hover:text-black ">
            Assignment marks
          </a>
        </li>
      </ul>
    </div>
  );
};

export default Sidebar;
