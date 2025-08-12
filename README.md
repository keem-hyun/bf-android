# 🌟 AI-powered Employment Support Platform for People with Disabilities – BAFF

> A personalized job-matching platform designed to help people with disabilities achieve successful employment.

## 📱 Project Overview

This project was initiated during the 8th UMC Hackathon with the goal of expanding job opportunities for people with disabilities through an Android application.<br>
It provides personalized job postings based on each user’s disability type, preferred industry, and working conditions,<br>
and aims to improve employment success rates through a simple and accessible application process.<br>

## 🗓️ Development Period

2025.07.05 – 2025.07.06 (36-hour hackathon, no-sleep)

## 🏅 Awards
Grand Prize – UMC 8th Hackathon

## ✨ Key Features

### 🏠 Home
- **Personalized Job Recommendations**: Tailored postings based on user profiles.
- **Swipe Interface**: Intuitive, card-based job browsing.
- **One-tap Apply**: Quickly apply to job listings of interest.

### 📋 Job Listings
- **Advanced Filtering**:
  - By disability type (e.g., physical, brain injury, visual, hearing, speech, facial, kidney, heart, liver, respiratory, ostomy, epilepsy, mental, developmental, etc.)
  - By location (nationwide by city/province)
  - By employment type (full-time, contract, internship, freelance, part-time, etc.)
  - By salary range & working hours
  - By education & experience requirements
- **Smart Recommendations**: AI-powered matching based on user data.

### 👤 My Page
- **Resume Management**: Detailed profile including personal info, disability type, and preferences.
- **Application History**: Track applied jobs.
- **Settings**: Manage preferences and personal information.

### 🔍 Search
- **Unified Search**: Search by job title, company name, or role.
- **Search History**: Save and manage recent searches.

### 📝 Application Process
- **Quick Apply**: Use saved resume data for one-click applications.
- **Personalized Message**: Add custom messages to applications.
- **Status Tracking**: Monitor application status and results.

## 🛠 Tech Stack

### Android
- **Kotlin**: Primary development language
- **Jetpack Compose**: Modern UI toolkit
- **Material Design 3**: Google’s latest design system

### Architecture & Libraries
- **MVVM Pattern**: Better maintainability through separation of concerns
- **Hilt**: Dependency injection
- **Retrofit**: REST API communication
- **Navigation Component**: Screen navigation management
- **StateFlow & LiveData**: Reactive data handling

### UI/UX
- **Custom Design System**: Accessibility-focused UI components
- **Pretendard Font**: Highly readable Korean font
- **Responsive Design**: Supports various screen sizes

## 🏗 Project Architecture

```
app/src/main/java/umc/hackathon/
├── core/                      # Core shared modules
│   ├── component/             # Reusable UI components
│   ├── designsystem/          # Colors, fonts, themes
│   ├── navigation/            # Navigation-related logic
│   └── ui/                    # Common UI utilities
├── data/                      # Data layer
│   ├── datasource/            # Local/remote data sources
│   ├── network/               # Network APIs
│   └── repository/            # Data repositories
├── domain/                    # Business logic
├── model/                     # Data models
├── presentation/              # Presentation layer
│   └── ui/                    # Screens
│       ├── main/              # Main screens
│       ├── home/              # Home screen
│       ├── jobpost/           # Job listings
│       ├── mypage/            # My Page
│       ├── search/            # Search features
│       └── apply/             # Application flow
└── di/                        # Dependency injection modules
```

## 📸 Screenshots

### Primary Screens

<div align="center">
  <img src="https://github.com/user-attachments/assets/f6731a00-d0ad-4753-babc-f56a4e8eb637" width="200" alt="홈 화면"/>
  <img src="https://github.com/user-attachments/assets/f9832512-8c92-4e55-bd20-ff9f183c5ee0" width="200" alt="채용 공고 목록"/>
  <img src="https://github.com/user-attachments/assets/0a028454-f9d2-4d2c-a974-13b5036de7bf" width="200" alt="마이 페이지"/>
</div>

<div align="center">
  <strong>Home</strong> | <strong>Job Listing</strong> | <strong>My Page</strong>
</div>


### [DEMO](https://youtu.be/l3YTwNZOLt8?si=RkAHxWBAi7Sj6AnN)
![데모 비디오](https://img.youtube.com/vi/l3YTwNZOLt8/0.jpg)


## 👥 Developer

| Role | Name | GitHub |
|------|------|--------|
| Android Developer | Kanghyun kim | [@keem-hyun](https://github.com/keem-hyun) |
| Android Developer | kyongrok kim | [@gomsang](https://github.com/gomsang) |
| Android Developer | juwan son | [@vvan2](https://github.com/vvan2) |
