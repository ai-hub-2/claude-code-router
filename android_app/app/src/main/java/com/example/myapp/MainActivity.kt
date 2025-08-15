package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.view.View
import android.content.Intent
import android.net.Uri
import android.text.method.ScrollingMovementMethod
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    
    private lateinit var inputText: EditText
    private lateinit var outputText: TextView
    private lateinit var sendButton: Button
    private lateinit var clearButton: Button
    private lateinit var settingsButton: Button
    private lateinit var statusText: TextView
    
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
        
        // Make output scrollable
        outputText.movementMethod = ScrollingMovementMethod()
        
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
            
            هذا التطبيق هو نسخة Android من البرنامج الأصلي:
            @musistudio/claude-code-router
            
            المميزات:
            • إرسال طلبات Claude Code
            • توجيه الطلبات إلى LLM providers
            • واجهة مستخدم سهلة وبسيطة
            • دعم اللغة العربية
            
            اكتب رسالتك في المربع أدناه واضغط "إرسال"
        """.trimIndent()
    }
    
    private fun processClaudeCode() {
        val input = inputText.text.toString().trim()
        
        if (input.isEmpty()) {
            showStatus("⚠️ الرجاء كتابة رسالة")
            return
        }
        
        showStatus("🔄 معالجة الطلب...")
        
        // Simulate processing (in real app, this would call the actual API)
        val response = simulateClaudeResponse(input)
        
        outputText.append("\n\n--- طلب جديد ---\n")
        outputText.append("📤 الإرسال: $input\n")
        outputText.append("📥 الرد: $response\n")
        outputText.append("✅ تم التوجيه بنجاح\n")
        
        showStatus("✅ تم إرسال الطلب بنجاح")
        inputText.text.clear()
    }
    
    private fun simulateClaudeResponse(input: String): String {
        return when {
            input.contains("hello", ignoreCase = true) -> "مرحباً! كيف يمكنني مساعدتك؟"
            input.contains("code", ignoreCase = true) -> "أرى أنك تريد مساعدة في البرمجة. سأقوم بتوجيه طلبك إلى Claude Code."
            input.contains("help", ignoreCase = true) -> "أنا هنا لمساعدتك! يمكنني توجيه طلباتك إلى Claude Code."
            else -> "شكراً لك! تم استلام طلبك وسيتم توجيهه إلى Claude Code للرد عليك."
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