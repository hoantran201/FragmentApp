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
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentapp.databinding.FragmentInfoBinding
import com.example.fragmentapp.ui.main.adapter.InfoAdapter
import com.example.fragmentapp.viewmodel.job.JobViewModel
import org.koin.android.ext.android.inject

class InfoFragment : Fragment() {

    private var binding: FragmentInfoBinding? = null
    private var recyclerView: RecyclerView? = null
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

            recyclerView = binding?.rvInfo
            recyclerView?.layoutManager = LinearLayoutManager(requireContext())
            binding?.formInfo?.edtAge?.hint = "Job Name"
            binding?.formInfo?.edtAge?.inputType = InputType.TYPE_CLASS_TEXT
            binding?.formInfo?.edtName?.hint = "Name"


            jobViewModel.getJobData().observe(viewLifecycleOwner, Observer { jobs ->
                val adapter = InfoAdapter(jobs)
                recyclerView?.adapter = adapter
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

            jobViewModel.addJobData(name,jobName)

            txtName.text.clear()
            txtJobName.text.clear()
        }
    }
}