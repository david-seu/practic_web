using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace PracticWeb.Migrations
{
    /// <inheritdoc />
    public partial class db_init1 : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_ChildClasses_ParentClasses_ParentId",
                table: "ChildClasses");

            migrationBuilder.DropForeignKey(
                name: "FK_ParentClasses_Users_UserId",
                table: "ParentClasses");

            migrationBuilder.DropPrimaryKey(
                name: "PK_ParentClasses",
                table: "ParentClasses");

            migrationBuilder.DropPrimaryKey(
                name: "PK_ChildClasses",
                table: "ChildClasses");

            migrationBuilder.DropIndex(
                name: "IX_ChildClasses_ParentId",
                table: "ChildClasses");

            migrationBuilder.RenameTable(
                name: "ParentClasses",
                newName: "Logs");

            migrationBuilder.RenameTable(
                name: "ChildClasses",
                newName: "Avatar");

            migrationBuilder.RenameColumn(
                name: "Name",
                table: "Logs",
                newName: "Text");

            migrationBuilder.RenameIndex(
                name: "IX_ParentClasses_UserId",
                table: "Logs",
                newName: "IX_Logs_UserId");

            migrationBuilder.RenameColumn(
                name: "ParentId",
                table: "Avatar",
                newName: "Rank");

            migrationBuilder.AddColumn<DateTime>(
                name: "Date",
                table: "Logs",
                type: "datetime2",
                nullable: false,
                defaultValue: new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified));

            migrationBuilder.AddColumn<int>(
                name: "Age",
                table: "Avatar",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<string>(
                name: "Powers",
                table: "Avatar",
                type: "nvarchar(max)",
                nullable: false,
                defaultValue: "");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Logs",
                table: "Logs",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Avatar",
                table: "Avatar",
                column: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Logs_Users_UserId",
                table: "Logs",
                column: "UserId",
                principalTable: "Users",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Logs_Users_UserId",
                table: "Logs");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Logs",
                table: "Logs");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Avatar",
                table: "Avatar");

            migrationBuilder.DropColumn(
                name: "Date",
                table: "Logs");

            migrationBuilder.DropColumn(
                name: "Age",
                table: "Avatar");

            migrationBuilder.DropColumn(
                name: "Powers",
                table: "Avatar");

            migrationBuilder.RenameTable(
                name: "Logs",
                newName: "ParentClasses");

            migrationBuilder.RenameTable(
                name: "Avatar",
                newName: "ChildClasses");

            migrationBuilder.RenameColumn(
                name: "Text",
                table: "ParentClasses",
                newName: "Name");

            migrationBuilder.RenameIndex(
                name: "IX_Logs_UserId",
                table: "ParentClasses",
                newName: "IX_ParentClasses_UserId");

            migrationBuilder.RenameColumn(
                name: "Rank",
                table: "ChildClasses",
                newName: "ParentId");

            migrationBuilder.AddPrimaryKey(
                name: "PK_ParentClasses",
                table: "ParentClasses",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_ChildClasses",
                table: "ChildClasses",
                column: "Id");

            migrationBuilder.CreateIndex(
                name: "IX_ChildClasses_ParentId",
                table: "ChildClasses",
                column: "ParentId");

            migrationBuilder.AddForeignKey(
                name: "FK_ChildClasses_ParentClasses_ParentId",
                table: "ChildClasses",
                column: "ParentId",
                principalTable: "ParentClasses",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_ParentClasses_Users_UserId",
                table: "ParentClasses",
                column: "UserId",
                principalTable: "Users",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
