import { BrowserRouter, Route, Routes } from "react-router-dom"
import Navbar from "./Navbar"
import Sidebar from "./Sidebar"
import MainPage from "./MainPage"
import Dashboard from "./Dashboard"
import TimeTable from "./TimeTable"
import SubjectRegistration from "./SubjectRegistration"
import ApplyLeave from "./ApplyLeave"
import Staff from "./Staff"
function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Sidebar />
      <Routes>
        <Route path="/" element={<MainPage />}>
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/timetable" element={<TimeTable />} />
          <Route path="/subject_registration" element={<SubjectRegistration />} />
          <Route path="/apply_leave" element={<ApplyLeave />} />
          <Route path="/staff" element={<Staff />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
