/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        primary: '',
        sercondary: '',
        sidebarColor: '#343A40',
        burgerMenu: '#4C4C4C'
      },
      fontFamily: {
        sans: ['"Source Sans Pro"', 'sans-serif'],
      }
    },
  },
  plugins: [],
}

