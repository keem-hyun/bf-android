package umc.hackathon.data.datasource

import umc.hackathon.model.JobPosting
import javax.inject.Inject

class MockJobPostingDataSource @Inject constructor() : JobPostingDataSource {
    
    override suspend fun getRecommendJobPostings(): List<JobPosting> {
        return mockJobPostings
    }
    
    override suspend fun getDetailJobPosting(id: Int): JobPosting? {
        return mockJobPostings.find { it.id == id }
    }
    
    companion object {
        private val mockJobPostings = listOf(
            JobPosting(
                id = 1,
                title = "[장애인 전형]사내 카페 지원",
                company = "주식회사 레진엔터테인먼트(LEZHINEnt.)",
                category = "외식음료 > 커피전문점",
                location = "서울 성동구",
                contractType = "계약직",
                workHours = "일 4시간",
                salary = "월급 110만원",
                jobType = "장애인 바리스타",
                duties = "사내 카페 운영 보조",
                recruitCount = "1명",
                experience = "무관",
                education = "고졸이상",
                disabilityType = "무관"
            ),
            JobPosting(
                id = 2,
                title = "[장애인전형/마포구] 현대기아차그룹 계열 사무직 인재 모집",
                company = "장애인잡",
                category = "사무직",
                location = "서울 마포구",
                contractType = "계약직",
                workHours = "일 9시간",
                salary = "월급 275만원",
                jobType = "면접 후 결정",
                duties = "면접 후 결정",
                recruitCount = "1명",
                experience = "무관",
                education = "고졸이상",
                disabilityType = "무관"
            ),
            JobPosting(
                id = 3,
                title = "[취업 🚀][SK행복나눔재단] 2025 하반기 청년 장애인 취업 연계 프로그램 훈련생 모집",
                company = "SK행복나눔재단",
                category = "기획·전략 > AI기획자",
                location = "지역 협의",
                contractType = "정규직",
                workHours = "일 7시간",
                salary = "월급 334,000원",
                jobType = "AI콘텐츠디자인, 경영관리 · 회계사무",
                duties = "콘텐츠디자인, 그래픽 디자인, 제안서 디자인, 회계/세무, 총무업무, 일반 사무",
                recruitCount = "직무별 10명",
                experience = "무관",
                education = "무관",
                disabilityType = "무관"
            ),
            JobPosting(
                id = 4,
                title = "[재택근무] 건설사 사무직 인재 모집 -7월 초 입사",
                company = "나라에이치알",
                category = "정보통신",
                location = "재택근무",
                contractType = "계약직",
                workHours = "일 3.5시간",
                salary = "월급 915,200원",
                jobType = "사원",
                duties = "팀원",
                recruitCount = "0명",
                experience = "무관",
                education = "무관",
                disabilityType = "중증우대"
            ),
            JobPosting(
                id = 5,
                title = "[조선비즈] 뉴스 사이트 모니터링 업무 모집",
                company = "조선비즈",
                category = "모니터링",
                location = "재택근무",
                contractType = "계약직",
                workHours = "일 6.5시간",
                salary = "월급 105만원",
                jobType = "사원급",
                duties = "팀원",
                recruitCount = "1명",
                experience = "무관",
                education = "대졸",
                disabilityType = "중증장애인 우대"
            ),
            JobPosting(
                id = 6,
                title = "[장애인전형/재택근무] 퍼시스그룹 장애인 재택근무 인재 모집",
                company = "장애인잡",
                category = "기획·전략 > 마케팅기획, 경영·비즈니스기획",
                location = "재택근무",
                contractType = "계약직",
                workHours = "일 4시간",
                salary = "월급 1,053,200원",
                jobType = "마케팅, 컨텐츠기획, 채널육성",
                duties = "국내외 오피스가구 인테리어 디자인 분야 SNS 레퍼런스 조사, CG/렌더링, 퍼시스 온라인 파트 영업 지원",
                recruitCount = "각 채용 부문 당 1명씩",
                experience = "무관",
                education = "무관",
                disabilityType = "중증장애인우대"
            ),
            JobPosting(
                id = 7,
                title = "[합정역] 브이엔티지 서비스기획 인재 모집",
                company = "나라에이치알",
                category = "기획·전략 > PL·PM·PO",
                location = "서울시 마포구",
                contractType = "정규직",
                workHours = "일 9시간",
                salary = "협의",
                jobType = "서비스기획자",
                duties = "솔루션 기획 및 UX/UI 기획, 프로젝트 제안 및 사업 발굴, 프로젝트 기획 파트 리딩",
                recruitCount = "0명",
                experience = "무관",
                education = "무관",
                disabilityType = "경증우대"
            ),
            JobPosting(
                id = 8,
                title = "[앤돌핀] 마케팅 홍보 및 블로그 내용 작성/업로드 등을 함께 해주실 분을 모십니다.",
                company = "(주)앤돌핀",
                category = "기획·전략 > 마케팅기획",
                location = "재택근무",
                contractType = "계약직",
                workHours = "일 3시간",
                salary = "월급 601,800원",
                jobType = "마케팅 블로그 내용 작성 등",
                duties = "마케팅 블로그 내용 작성 등, 관련 트랜드 리서치 등",
                recruitCount = "0명",
                experience = "무관",
                education = "무관",
                disabilityType = "무관"
            ),
            JobPosting(
                id = 9,
                title = "2025년 그랜드코리아레저(주) 신입사원 채용공고",
                company = "그랜드코리아레저(주)",
                category = "분야 협의",
                location = "서울 강남구",
                contractType = "정규직",
                workHours = "시간 협의",
                salary = "협의",
                jobType = "사원급",
                duties = "팀원",
                recruitCount = "총 47명",
                experience = "신입",
                education = "무관",
                disabilityType = "무관"
            ),
            JobPosting(
                id = 10,
                title = "[재택근무]주5일4시간 사무보조_중증 장애인 근로자 채용 공고",
                company = "더봄플러스",
                category = "기획·전략 > 마케팅기획",
                location = "재택근무",
                contractType = "계약직",
                workHours = "일 4시간",
                salary = "월급 1,048,140원",
                jobType = "사무보조",
                duties = "마케팅 관련 업무, 경쟁업체 HR 리서치, 브랜드 관리",
                recruitCount = "0명",
                experience = "무관",
                education = "고졸",
                disabilityType = "중증장애인 우대"
            )
        )
    }
}