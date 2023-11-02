//package com.konsola5.modifiers;
//
//import net.minecraft.item.ItemStack;
//import team.reborn.energy.api.EnergyStorage;
//import team.reborn.energy.api.base.SimpleEnergyItem;
//
//class EnergyBaseModifier implements SimpleEnergyItem {
//    @Override
//    public long getEnergyCapacity(ItemStack stack) {
//        return 10000;
//    }
//
//    @Override
//    public long getEnergyMaxInput(ItemStack stack) {
//        return 100;
//    }
//
//    @Override
//    public long getEnergyMaxOutput(ItemStack stack) {
//        return 100;
//    }
//
//    @Override
//    public long getStoredEnergy(ItemStack stack) {
//        return SimpleEnergyItem.super.getStoredEnergy(stack);
//    }
//
//    @Override
//    public void setStoredEnergy(ItemStack stack, long newAmount) {
//        SimpleEnergyItem.super.setStoredEnergy(stack, newAmount);
//    }
//
//    @Override
//    public boolean tryUseEnergy(ItemStack stack, long amount) {
//        return SimpleEnergyItem.super.tryUseEnergy(stack, amount);
//    }
//}