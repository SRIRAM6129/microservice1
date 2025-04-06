const Navbar = () => {
  return (
    <div className=
      "fixed top-0 left-[250px] h-16 w-[calc(100%-250px)] bg-white text-white flex items-center justify-between flex-row px-6 border-b-2 border-gray-200">
      <div className="w-[10%] h-4 flex flex-col justify-between cursor-pointer">
        <div className="w-4 h-0.5 bg-black rounded"></div>
        <div className="w-4 h-0.5 bg-black rounded"></div>
        <div className="w-4 h-0.5 bg-black rounded"></div>
      </div>
      <div className="h-full w-[10%] px-2 py-2">
        <button className="w-full h-full  text-white rounded-sm bg-blue-500"> </button>
      </div>
    </div>
  );
};

export default Navbar;
