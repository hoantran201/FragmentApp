package com.example.fragmentapp.ui.fragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentapp.databinding.FragmentInfoBinding
import com.example.fragmentapp.ui.adapter.InfoAdapter
import com.example.fragmentapp.viewmodel.job.JobViewModel
import org.koin.android.ext.android.inject

class InfoFragment : Fragment() {

    private var binding: FragmentInfoBinding? = null
    private val jobViewModel: JobViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {

            var recyclerView = it.rvInfo
            recyclerView.layoutManager = LinearLayoutManager(context)
            it.formInfo.edtAge.hint = "Job Name"
            it.formInfo.edtAge.inputType = InputType.TYPE_CLASS_TEXT
            it.formInfo.edtName.hint = "Name"

            jobViewModel.getJobData().observe(viewLifecycleOwner, Observer { jobs ->
                recyclerView.adapter = InfoAdapter(jobs)
            })

            it.formInfo.btnSubmit.setOnClickListener {
                addJob()
            }
        }
    }

    fun addJob() {
        binding?.let {

            var txtName: EditText = it.formInfo.edtName
            var txtJobName = it.formInfo.edtAge

            var jobName = txtJobName.text.toString()
            var name = txtName.text.toString()

            jobViewModel.addJobData(name, jobName)

            txtName.text.clear()
            txtJobName.text.clear()
        }
    }
}