package com.example.myapp

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class ClaudeService {
    private val client = OkHttpClient()
    private val mediaType = "application/json; charset=utf-8".toMediaType()
    
    // Base URL for the Claude Code Router (you can change this)
    private val baseUrl = "http://localhost:3000" // Default local server
    
    suspend fun sendClaudeRequest(message: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            val json = JSONObject().apply {
                put("message", message)
                put("model", "claude-3-sonnet-20240229")
            }
            
            val requestBody = json.toString().toRequestBody(mediaType)
            
            val request = Request.Builder()
                .url("$baseUrl/api/claude")
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .build()
            
            val response = client.newCall(request).execute()
            
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                Log.d("ClaudeService", "Response: $responseBody")
                Result.success(responseBody ?: "No response body")
            } else {
                Log.e("ClaudeService", "Error: ${response.code} - ${response.message}")
                Result.failure(Exception("HTTP ${response.code}: ${response.message}"))
            }
        } catch (e: Exception) {
            Log.e("ClaudeService", "Exception: ${e.message}", e)
            Result.failure(e)
        }
    }
    
    suspend fun checkServerStatus(): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url("$baseUrl/health")
                .get()
                .build()
            
            val response = client.newCall(request).execute()
            Result.success(response.isSuccessful)
        } catch (e: Exception) {
            Log.e("ClaudeService", "Server check failed: ${e.message}", e)
            Result.failure(e)
        }
    }
}