package com.example.recyclerview

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_timezone_row.*
import kotlinx.android.synthetic.main.list_timezone_row.view.*
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SampleAdapter(this) { timezone ->
            Toast.makeText(this, timezone.displayName, Toast.LENGTH_SHORT).show()
        }

        timezoneList.adapter = adapter

        timezoneList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}

class SampleAdapter(context: Context,
    private val onItemClicked: (TimeZone) -> Unit) : RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private val timezones = TimeZone.getAvailableIDs().map {
        id -> TimeZone.getTimeZone(id)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SampleViewHolder {
        val view = inflater.inflate(R.layout.list_timezone_row, p0, false)

        val viewHolder = SampleViewHolder(view)

        view.setOnClickListener {
            val position = viewHolder.adapterPosition
            val timezone = timezones[position]
            onItemClicked(timezone)
        }

        return viewHolder
    }

    override fun getItemCount() = timezones.size

    override fun onBindViewHolder(p0: SampleViewHolder, p1: Int) {
        val timezone = timezones[p1]
        p0.timezone.text = timezone.id
    }

    class SampleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val timezone = view.timezone
    }
}