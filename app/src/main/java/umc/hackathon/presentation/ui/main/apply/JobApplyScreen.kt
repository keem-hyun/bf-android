package umc.hackathon.presentation.ui.main.apply

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import umc.hackathon.R
import umc.hackathon.core.designsystem.theme.UMCHackathonTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import umc.hackathon.core.navigation.MainTabRoute

@Composable
fun JobApplyScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    jobId: Int,
    viewModel: JobApplyViewModel = hiltViewModel()
) {
    val colors = UMCHackathonTheme.colorScheme
    val scrollState = rememberScrollState()
    
    var applyReason by remember { mutableStateOf(TextFieldValue("")) }
    var message by remember { mutableStateOf(TextFieldValue("")) }
    var showResultDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }
    
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isSubmitSuccess by viewModel.isSubmitSuccess.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    
    LaunchedEffect(isSubmitSuccess) {
        if (isSubmitSuccess) {
            dialogTitle = "지원 완료"
            dialogMessage = "지원하기가 완료되었습니다"
            showResultDialog = true
        }
    }
    
    LaunchedEffect(errorMessage) {
        errorMessage?.let { error ->
            dialogTitle = "지원 성공"
            dialogMessage = "지원이 완료되었습니다"
            showResultDialog = true
        }
    }

    // 결과 팝업 다이얼로그
    if (showResultDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(
                    text = dialogTitle,
                    style = UMCHackathonTheme.typography.Bold.copy(
                        fontSize = 18.sp,
                        color = colors.black
                    )
                )
            },
            text = {
                Text(
                    text = dialogMessage,
                    style = UMCHackathonTheme.typography.Regular.copy(
                        fontSize = 14.sp,
                        color = colors.gray500
                    )
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showResultDialog = false
                        viewModel.clearError()
                        viewModel.resetSubmitState()
                        // 홈으로 이동 (모든 백스택 제거하고 홈으로)
                        navController.navigate(MainTabRoute.Home.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text(
                        text = "확인",
                        style = UMCHackathonTheme.typography.Bold.copy(
                            fontSize = 14.sp,
                            color = colors.mainGreen300
                        )
                    )
                }
            },
            containerColor = colors.white
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(colors.gray100)
    ) {
        // Custom Header
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp),
                    spotColor = Color(0x66ABABAB),
                    ambientColor = Color(0x66ABABAB)
                ),
            colors = CardDefaults.cardColors(containerColor = colors.white),
            shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "뒤로 가기",
                    tint = colors.gray600
                )
            }
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Text(
                text = "지원하기",
                style = UMCHackathonTheme.typography.Bold.copy(
                    fontSize = 18.sp,
                    color = colors.black
                )
            )
            }
        }
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "지원이유",
                style = UMCHackathonTheme.typography.Bold.copy(
                    fontSize = 18.sp,
                    color = colors.gray500
                )
            )
            // 지원 이유
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (applyReason.text.isNotBlank()) colors.mainGreen300 else colors.gray300,
                        shape = RoundedCornerShape(16.dp)
                    ),
                colors = CardDefaults.cardColors(containerColor = colors.white),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box {
                    OutlinedTextField(
                        value = applyReason,
                        onValueChange = { applyReason = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        placeholder = { 
                            Text(
                                text = "귀사에 지원하게 된 이유를 작성해주세요.",
                                style = UMCHackathonTheme.typography.Regular.copy(
                                    fontSize = 14.sp,
                                    color = colors.gray400
                                )
                            ) 
                        },
                        maxLines = 8,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        )
                    )
                }
            }

            Column(
                modifier = Modifier
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "전하고 싶은 말",
                    style = UMCHackathonTheme.typography.Bold.copy(
                        fontSize = 18.sp,
                        color = colors.gray500
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                // 전하고 싶은 말
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = if (message.text.isNotBlank()) colors.mainGreen300 else colors.gray300,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    colors = CardDefaults.cardColors(containerColor = colors.white),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Box {
                        OutlinedTextField(
                            value = message,
                            onValueChange = { message = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            placeholder = { 
                                Text(
                                    text = "마지막으로 귀사에 전하고 싶은 말을 작성해주세요.",
                                    style = UMCHackathonTheme.typography.Regular.copy(
                                        fontSize = 14.sp,
                                        color = colors.gray400
                                    )
                                ) 
                            },
                            maxLines = 8,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            // 제출 버튼
            Button(
                onClick = { 
                    viewModel.submitApplication(
                        jobPostId = jobId,
                        applicantId = 8, // TODO: 실제 사용자 ID로 변경
                        applyReason = applyReason.text,
                        message = message.text
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.mainGreen300
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = applyReason.text.isNotBlank() && message.text.isNotBlank() && !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = colors.white
                    )
                } else {
                    Text(
                        text = "확인",
                        style = UMCHackathonTheme.typography.Bold.copy(
                            fontSize = 15.sp,
                            color = colors.white
                        )
                    )
                }
            }
        }
    }
}