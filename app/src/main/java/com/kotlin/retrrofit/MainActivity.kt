package com.kotlin.retrrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.retrrofit.response.CommentResponse
import com.kotlin.retrrofit.response.PostResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<PostResponse>()
    private val listComment = ArrayList<CommentResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        showPosts()
//        createPost()
        showComment()
//        updatePost()
//        deletePost()
    }

    private fun deletePost() {
        RetrofitClient.instance.deletePost(1).enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                tvResponseCode.text = response.code().toString()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        })
    }

    private fun updatePost() {
        RetrofitClient.instance.putPost(
            5,
            4,
            5,
            null,
            "body berubah"
        ).enqueue(object: Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                tvResponseCode.text = response.code().toString()
                val responseText = "Response Code: ${response.code()}\n" +
                        "id: ${response.body()?.id}\n" +
                        "userid: ${response.body()?.userId}\n" +
                        "title: ${response.body()?.title}\n" +
                        "body: ${response.body()?.body}"

                tvResponseCode.text = responseText
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        })
    }

    private fun showComment() {
        rvPost.setHasFixedSize(true)
        rvPost.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getComment("posts/2/comments").enqueue(object: Callback<ArrayList<CommentResponse>> {
            override fun onResponse(
                call: Call<ArrayList<CommentResponse>>,
                response: Response<ArrayList<CommentResponse>>
            ) {
                tvResponseCode.text = response.code().toString()
                response.body()?.let { listComment.addAll(it) }
                val adapter = CommentAdapter(listComment)
                rvPost.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<CommentResponse>>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        })
    }

    private fun createPost() {
        RetrofitClient.instance.createPost(
            29,
            "Retrofit",
            "Retrofit Body"
        ).enqueue(object: Callback<CreatePostResponse>{
            override fun onResponse(
                call: Call<CreatePostResponse>,
                response: Response<CreatePostResponse>
            ) {
                val responseText = "Response Code: ${response.code()}\n" +
                        "id: ${response.body()?.id}\n" +
                        "userid: ${response.body()?.userId}\n" +
                        "title: ${response.body()?.title}\n" +
                        "body: ${response.body()?.body}"

                tvResponseCode.text = responseText
            }

            override fun onFailure(call: Call<CreatePostResponse>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        })
    }

    private fun showPosts() {
        rvPost.setHasFixedSize(true)
        rvPost.layoutManager = LinearLayoutManager(this)

        val parameters = HashMap<String, String>()
        parameters["userId"] = "4"
        parameters["id"] = "32"

        RetrofitClient.instance.getPosts(parameters).enqueue(object: Callback<ArrayList<PostResponse>> {
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response: Response<ArrayList<PostResponse>>
            ) {
                val responseCode = response.code().toString()
                tvResponseCode.text = responseCode
                response.body()?.let { list.addAll(it) }
                val adapter = PostAdapter(list)
                rvPost.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}