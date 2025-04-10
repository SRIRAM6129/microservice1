import axios from 'axios';
import React, { useState } from 'react';
import * as XLSX from 'xlsx';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable'; // ✅ required for jspdf@3+

const Dashboard = () => {
  const [batch, setBatch] = useState('');
  const [academicYear, setAcademicYear] = useState('');
  const [department, setDepartment] = useState('');
  const [section, setSection] = useState('');
  const [studentDetails, setStudentDetails] = useState([
    {
      id: 1,
      name: "John Doe",
      batchId: "B001",
      academicYear: "2",
      section: "A",
      department: "CSE"
    },
    {
      id: 2,
      name: "Jane Smith",
      batchId: "B002",
      academicYear: "3",
      section: "B",
      department: "ECE"
    }
  ]);

  const handleBatchChange = (e) => setBatch(e.target.value);
  const handleDepartmentChange = (e) => setDepartment(e.target.value);
  const handleSectionChange = (e) => setSection(e.target.value);
  const handleAcademicYear = (e) => setAcademicYear(e.target.value);

  const handleSend = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.get(`http://localhost:8080/STUDENT-SERVICE/student/get/table`, {
        params: { batchId: batch, academicYear, deptId: department, section }
      });
      setStudentDetails(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const exportToExcel = () => {
    const worksheet = XLSX.utils.json_to_sheet(studentDetails.map((s, i) => ({
      "S.no": i + 1,
      "Name": s.name,
      "Batch": s.batchId,
      "Academic Year": s.academicYear,
      "Section": s.section,
      "Department": s.departement
    })));
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, "Students");
    XLSX.writeFile(workbook, "Student_Report.xlsx");
  };

  const exportToPDF = () => {
    const doc = new jsPDF();
    doc.text("Student Report", 14, 15);

    autoTable(doc, {
      head: [["S.no", "Name", "Batch", "Academic Year", "Section", "Department"]],
      body: studentDetails.map((s, i) => [
        i + 1,
        s.name,
        s.batchId,
        s.academicYear,
        s.section,
        s.departement
      ]),
      startY: 20
    });

    doc.save("Student_Report.pdf");
  };

  return (
    <div className="Dashboard p-4">
      <form className="flex flex-wrap gap-4 mb-4 border-2 p-4">
        <select value={batch} onChange={handleBatchChange} className='w-full md:w-1/5 border p-2 rounded'>
          <option value="">Select Batch</option>
          <option value={1}>Batch-1</option>
          <option value={2}>Batch-2</option>
          <option value={3}>Batch-3</option>
          <option value={4}>Batch-4</option>
        </select>

        <select value={academicYear} onChange={handleAcademicYear} className='w-full md:w-1/5 border p-2 rounded'>
          <option value="">Select Academic Year</option>
          <option value={1}>1</option>
          <option value={2}>2</option>
          <option value={3}>3</option>
          <option value={4}>4</option>
        </select>

        <select value={department} onChange={handleDepartmentChange} className='w-full md:w-1/5 border p-2 rounded'>
          <option value="">Select Department</option>
          <option value={1}>CSE</option>
          <option value={2}>AIDS</option>
          <option value={3}>AIML</option>
          <option value={4}>ECE</option>
        </select>

        <select value={section} onChange={handleSectionChange} className='w-full md:w-1/5 border p-2 rounded'>
          <option value="">Select Section</option>
          <option value={1}>A</option>
          <option value={2}>B</option>
          <option value={3}>C</option>
          <option value={4}>D</option>
        </select>

        <div className="flex gap-2 w-full md:w-auto">
          <button onClick={handleSend} className="px-4 py-2 bg-blue-500 text-white rounded" type="submit">SUBMIT</button>
          <button onClick={exportToPDF} className="px-4 py-2 bg-red-500 text-white rounded" type="button">PDF</button>
          <button onClick={exportToExcel} className="px-4 py-2 bg-green-500 text-white rounded" type="button">EXCEL</button>
        </div>
      </form>

      <table className="w-full border-collapse border">
        <thead>
          <tr className="bg-gray-200">
            <th className="border px-4 py-2">S.no</th>
            <th className="border px-4 py-2">Name</th>
            <th className="border px-4 py-2">Batch</th>
            <th className="border px-4 py-2">Academic Year</th>
            <th className="border px-4 py-2">Section</th>
            <th className="border px-4 py-2">Department</th>
          </tr>
        </thead>
        <tbody>
          {studentDetails.map((student, index) => (
            <tr key={student.id} className="hover:bg-gray-100">
              <td className="border px-4 py-2">{index + 1}</td>
              <td className="border px-4 py-2">{student.name}</td>
              <td className="border px-4 py-2">{student.batchId}</td>
              <td className="border px-4 py-2">{student.academicYear}</td>
              <td className="border px-4 py-2">{student.section}</td>
              <td className="border px-4 py-2">{student.departement}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Dashboard;
