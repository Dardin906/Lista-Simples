package com.example.listasimples

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton. FloatingActionButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etnovatarefa = findViewById<EditText>(R.id.etnovatarefa)
        val btadd = findViewById<Button>(R.id.btadd)
        val tvTitulo = findViewById<TextView>(R.id.tvtitulo)
        val lvTarefas = findViewById<ListView>(R.id.lvtarefas)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            tvTitulo.isVisible = false
            etnovatarefa.isVisible = true
            btadd.isVisible = true
        }

        val listaTarefas: ArrayList<String> = ArrayList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaTarefas)
        lvTarefas.adapter = adapter

        btadd.setOnClickListener {
            if (etnovatarefa.text.isNullOrEmpty()) {
                Toast.makeText(this, "Digite uma tarefa...", Toast.LENGTH_SHORT).show()
            } else {
                listaTarefas.add(etnovatarefa.text.toString())
                //notificamos ao adaptar que tivemos alteração da lista
                //notificado, ele atualiza os novos elementos da lista na tela
                adapter.notifyDataSetChanged()
                etnovatarefa.setText("")
            }
        }
        lvTarefas.setOnItemLongClickListener { _, _, position, _ ->
            //aqui montamos a caixa de diálogo
            val alerta = AlertDialog.Builder(this)
            alerta.setTitle("Atenção!")
            alerta.setMessage("Quer Mesmo Excluir Esse Item?")
            alerta.setPositiveButton("Confirmar") { dialog, _ ->

                //caso o botão confirmar seja clicado, remover o item da lista
                listaTarefas.removeAt(position)
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            alerta.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            alerta.create().show()
            true
        }
    }




}