# Attendance Concept

## Model 1
- Each user can :
  - Check-in and check-out once per day
  - attendance is tied to date, not a session or subject

## User Flow (Web Based)
- Check-in Flow
  - Users open website
  - User enter identifier (student ID / Username)
  - clicks *Check-in*
  - System :
      - Records current server time (SpringBoot localdate.now)
      - Saves attendance for today
      - Rejects duplicate check-in
- Check-out Flow
  - User clicks Check-out
  - System :
      - Ensures user already checked in
      - Records check-out time
      - Rejects multiple check-outs

## Core bussiness rules
- Check-in Rules
  - A user cannot check in twice on the same date
  - Check-in time is taken from server, not browser time
- Check-out Rules
  - A user must check in before checking out
  - a user cannot check out twice
  - check out must be after check-in
 
## Device & Browser Scope
- What is supported
  - Desktop browser
  - Laptop browsers
  - Tablet browsers
  - Mobile browsers (Basic support)
  
- What is NOT supported (for now)
  - Native android/IOS apps
  - Offline check-in
  - Background tracking

## Security Scope
- Version 1 (Now)
  - User identifies themselves via :
    - Student Id
    - Simple username
  - No authentication
  - No JSON Web Token
- Version 2 (Planned)
  - User authentication
  - JWT-based security
  - Role-based access control
  

