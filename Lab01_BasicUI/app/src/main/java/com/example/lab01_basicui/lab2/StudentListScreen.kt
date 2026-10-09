package com.example.lab01_basicui.lab2

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen() {
    val context = LocalContext.current

    // Danh sách sinh viên mẫu
    val sampleStudents = remember {
        listOf(
            Student(1, "Nguyễn Văn An", "Công nghệ thông tin"),
            Student(2, "Trần Thị Bích", "Công nghệ thông tin"),
            Student(3, "Lê Hoàng Minh", "Công nghệ thông tin"),
            Student(4, "Phạm Đăng Khoa", "Khoa học máy tính"),
            Student(5, "Đỗ Thị Thu", "Hệ thống thông tin")
        )
    }

    // State ô tìm kiếm theo tên
    var searchQuery by remember { mutableStateOf("") }

    // Lọc danh sách sinh viên theo tên
    val filteredStudents = remember(searchQuery, sampleStudents) {
        if (searchQuery.isBlank()) {
            sampleStudents
        } else {
            sampleStudents.filter {
                it.name.contains(searchQuery.trim(), ignoreCase = true)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Danh sách sinh viên",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Menu action */ }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Search action */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF00796B)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(context, "Thêm sinh viên mới", Toast.LENGTH_SHORT).show()
                },
                containerColor = Color(0xFF00796B),
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Thêm sinh viên"
                )
            }
        }
    ) { paddingValues ->
        // Bắt buộc truyền paddingValues vào content để tránh bị TopBar đè lên list
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Ô tìm kiếm theo tên
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                placeholder = {
                    Text(
                        text = "Tìm kiếm theo tên...",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Tìm kiếm",
                        tint = Color(0xFF00796B)
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Xóa tìm kiếm"
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF00796B),
                    unfocusedBorderColor = Color(0xFFBDBDBD),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // LazyColumn danh sách sinh viên với key = { it.id }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(
                    items = filteredStudents,
                    key = { student -> student.id }
                ) { student ->
                    StudentCard(
                        student = student,
                        onDetailClick = { selectedStudent ->
                            Toast.makeText(
                                context,
                                selectedStudent.name,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudentListScreenPreview() {
    StudentListScreen()
}
