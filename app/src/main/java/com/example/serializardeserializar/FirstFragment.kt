package com.example.serializardeserializar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.serializardeserializar.Model.User
import com.example.serializardeserializar.databinding.FragmentFirstBinding
import com.example.serializardeserializar.helper.Cast
import com.example.serializardeserializar.helper.toClass
import com.example.serializardeserializar.helper.toList
import org.json.JSONArray
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        serializar()
        return binding.root

    }

    fun serializar() {
        var oUser = User()
        oUser.ID = 0
        oUser.Nombre = "Juan Diego"
        oUser.Edad = 18

        val JSON =
            "[{ ID:4,Nombre:\"Juan Diego\",Edad:19,Genero={ID=1,Nombre=\"Masculino\"}},{ ID:5,Nombre:\"Juan Diego\",Edad:19,Genero={ID=1,Nombre=\"Masculino\"}}]"
        JSONArray(JSON).toList(User().javaClass.name).Cast<List<User>>().forEach {
            Log.e("Valores", "serializar: $it" )
        }
        binding.txtText.text = oUser.toString()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}