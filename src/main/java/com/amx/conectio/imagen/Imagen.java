package com.amx.conectio.imagen;


import com.amx.conectio.evento.Evento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image_data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Imagen {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String type;

    @Lob
    @Column(name = "imagedata", length = 65555)
    private byte[] imageData;
    
    @OneToOne(mappedBy = "imagen", optional = true)
    private Evento evento;
}