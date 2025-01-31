package it.unibs.ing.model.user;

import it.unibs.ing.model.comprensorio.IComprensorio;

public interface IFruitore extends IUser {
    IComprensorio getComprensiorio();
}