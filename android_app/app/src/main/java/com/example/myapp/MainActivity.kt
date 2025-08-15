package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.view.View
import android.content.Intent
import android.net.Uri
import android.text.method.ScrollingMovementMethod
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    
    private lateinit var inputText: EditText
    private lateinit var outputText: TextView
    private lateinit var sendButton: Button
    private lateinit var clearButton: Button
    private lateinit var settingsButton: Button
    private lateinit var statusText: TextView
    private lateinit var serverUrlInput: EditText
    
    private val claudeService = ClaudeService()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize views
        inputText = findViewById(R.id.inputText)
        outputText = findViewById(R.id.outputText)
        sendButton = findViewById(R.id.sendButton)
        clearButton = findViewById(R.id.clearButton)
        settingsButton = findViewById(R.id.settingsButton)
        statusText = findViewById(R.id.statusText)
        serverUrlInput = findViewById(R.id.serverUrlInput)
        
        // Make output scrollable
        outputText.movementMethod = ScrollingMovementMethod()
        
        // Set default server URL
        serverUrlInput.setText("http://localhost:3000")
        
        // Set up click listeners
        sendButton.setOnClickListener {
            processClaudeCode()
        }
        
        clearButton.setOnClickListener {
            clearAll()
        }
        
        settingsButton.setOnClickListener {
            openSettings()
        }
        
        // Show welcome message
        showStatus("مرحباً بك في Claude Code Router! 🎉")
        outputText.text = """
            Claude Code Router - Android Version
            
            هذا التطبيق يتصل بالبرنامج الأصلي:
            @musistudio/claude-code-router
            
            المميزات:
            • إرسال طلبات حقيقية إلى Claude Code
            • توجيه الطلبات إلى LLM providers
            • واجهة مستخدم سهلة وبسيطة
            • دعم اللغة العربية
            
            تأكد من تشغيل الخادم المحلي على المنفذ 3000
            اكتب رسالتك في المربع أدناه واضغط "إرسال"
        """.trimIndent()
        
        // Check server status on startup
        checkServerStatus()
    }
    
    private fun processClaudeCode() {
        val input = inputText.text.toString().trim()
        
        if (input.isEmpty()) {
            showStatus("⚠️ الرجاء كتابة رسالة")
            return
        }
        
        showStatus("🔄 إرسال الطلب إلى Claude Code...")
        sendButton.isEnabled = false
        
        lifecycleScope.launch {
            try {
                val result = claudeService.sendClaudeRequest(input)
                
                result.fold(
                    onSuccess = { response ->
                        runOnUiThread {
                            handleSuccessResponse(input, response)
                        }
                    },
                    onFailure = { exception ->
                        runOnUiThread {
                            handleErrorResponse(input, exception.message ?: "خطأ غير معروف")
                        }
                    }
                )
            } catch (e: Exception) {
                runOnUiThread {
                    handleErrorResponse(input, e.message ?: "خطأ غير معروف")
                }
            } finally {
                runOnUiThread {
                    sendButton.isEnabled = true
                }
            }
        }
    }
    
    private fun handleSuccessResponse(input: String, response: String) {
        outputText.append("\n\n--- طلب جديد ---\n")
        outputText.append("📤 الإرسال: $input\n")
        outputText.append("📥 الرد: $response\n")
        outputText.append("✅ تم التوجيه بنجاح\n")
        
        showStatus("✅ تم إرسال الطلب بنجاح")
        inputText.text.clear()
    }
    
    private fun handleErrorResponse(input: String, error: String) {
        outputText.append("\n\n--- خطأ في الطلب ---\n")
        outputText.append("📤 الإرسال: $input\n")
        outputText.append("❌ الخطأ: $error\n")
        outputText.append("🔧 تأكد من تشغيل الخادم المحلي\n")
        
        showStatus("❌ فشل في إرسال الطلب: $error")
    }
    
    private fun checkServerStatus() {
        lifecycleScope.launch {
            try {
                val result = claudeService.checkServerStatus()
                result.fold(
                    onSuccess = { isOnline ->
                        runOnUiThread {
                            if (isOnline) {
                                showStatus("✅ الخادم متصل")
                            } else {
                                showStatus("⚠️ الخادم غير متصل")
                            }
                        }
                    },
                    onFailure = { exception ->
                        runOnUiThread {
                            showStatus("❌ لا يمكن الاتصال بالخادم")
                        }
                    }
                )
            } catch (e: Exception) {
                runOnUiThread {
                    showStatus("❌ خطأ في الاتصال بالخادم")
                }
            }
        }
    }
    
    private fun clearAll() {
        inputText.text.clear()
        outputText.text = "تم مسح المحتوى"
        showStatus("🗑️ تم مسح المحتوى")
    }
    
    private fun openSettings() {
        showStatus("⚙️ فتح الإعدادات...")
        // In real app, this would open settings activity
        Toast.makeText(this, "الإعدادات قيد التطوير", Toast.LENGTH_SHORT).show()
    }
    
    private fun showStatus(message: String) {
        statusText.text = message
    }
}